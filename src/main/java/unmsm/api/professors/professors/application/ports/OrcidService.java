package unmsm.api.professors.professors.application.ports;

import java.io.ByteArrayInputStream;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import unmsm.api.professors.professors.application.dto.OrcidResponse;
import unmsm.api.professors.professors.domain.ports.input.OrcidUseCase;

/**
 * @author garyn
 * @date 14/06/2025 16:50
 */
@Service
public class OrcidService implements OrcidUseCase {

  private final WebClient webClient;

  private static final Logger logger = LoggerFactory.getLogger(OrcidService.class);

  @Autowired
  public OrcidService(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Optional<String> getOrcidId(String name) {
    String nameFormatted = name.replace(" ", "+");
    String url =
        "/expanded-search?q="
            + nameFormatted
            + "+AND+affiliation-org-name:Universidad+Nacional+Mayor+de+San+Marcos&start=0&rows=1";

    String xml = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();

    Pattern pattern = Pattern.compile("<expanded-search:orcid-id>(.*?)</expanded-search:orcid-id>");
    if (xml != null) {
      Matcher matcher = pattern.matcher(xml);
      if (matcher.find()) {
        return Optional.of(matcher.group(1));
      }
    }

    return Optional.empty();
  }

  @Override
  public OrcidResponse getOrcidInfo(String orcidId) {
    OrcidResponse response = new OrcidResponse();
    response.setOrcidId(orcidId);

    try {
      byte[] xmlData =
          webClient
              .get()
              .uri("/{orcidId}/record", orcidId)
              .accept(MediaType.APPLICATION_XML)
              .retrieve()
              .bodyToMono(byte[].class)
              .block();

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      assert xmlData != null;
      Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xmlData));

      XPath xpath = XPathFactory.newInstance().newXPath();
      xpath.setNamespaceContext(
          new NamespaceContext() {
            @Override
            public String getNamespaceURI(String prefix) {
              return switch (prefix) {
                case "person" -> "http://www.orcid.org/ns/person";
                case "keyword" -> "http://www.orcid.org/ns/keyword";
                case "activities" -> "http://www.orcid.org/ns/activities";
                case "employment" -> "http://www.orcid.org/ns/employment";
                case "common" -> "http://www.orcid.org/ns/common";
                case "history" -> "http://www.orcid.org/ns/history";
                case "work" -> "http://www.orcid.org/ns/work";
                default -> null;
              };
            }

            @Override
            public String getPrefix(String uri) {
              return null;
            }

            @Override
            public Iterator<String> getPrefixes(String uri) {
              return null;
            }
          });

      NodeList keywords =
          (NodeList)
              xpath.evaluate(
                  "//person:person/keyword:keywords/keyword:keyword/keyword:content/text()",
                  doc,
                  XPathConstants.NODESET);
      if (keywords.getLength() > 0) {
        response.setKeyWords(
            IntStream.range(0, keywords.getLength())
                .mapToObj(i -> keywords.item(i).getNodeValue().trim())
                .collect(Collectors.joining(", ")));
      }

      Node jobNode =
          (Node)
              xpath.evaluate(
                  "(//activities:activities-summary/activities:employments/activities:affiliation-group/employment:employment-summary/common:role-title)[1]/text()",
                  doc,
                  XPathConstants.NODE);
      if (jobNode != null) {
        response.setCurrentJob(jobNode.getNodeValue().trim());
      }

      response.setPublications(
          (Boolean)
              xpath.evaluate(
                  "count(//activities:works/work:work) > 0", doc, XPathConstants.BOOLEAN));

      Node dateNode =
          (Node)
              xpath.evaluate(
                  "//history:history/common:last-modified-date/text()", doc, XPathConstants.NODE);
      if (dateNode != null) {
        response.setLastUpdated(ZonedDateTime.parse(dateNode.getNodeValue()).toLocalDate());
      }

    } catch (Exception e) {
      logger.info("Error getting ORCID {} : {} ", orcidId, e.getMessage());
    }
    return response;
  }
}
