package unmsm.api.professors.professors.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author garyn
 * @date 14/06/2025 09:46
 */
@Configuration
public class BeanConfig {

  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .exchangeStrategies(
            ExchangeStrategies.builder()
                .codecs(
                    configurer ->
                        configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)) // 16MB
                .build())
        .baseUrl("https://pub.orcid.org/v3.0")
        .build();
  }
}
