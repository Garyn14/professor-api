package unmsm.api.professors.professors.application.ports;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unmsm.api.professors.professors.application.dto.ProfessorResponse;
import unmsm.api.professors.professors.domain.model.Professor;
import unmsm.api.professors.professors.domain.ports.input.OrcidUseCase;
import unmsm.api.professors.professors.domain.ports.input.ProfessorUseCase;
import unmsm.api.professors.professors.domain.ports.output.ProfessorRepositoryPort;

/**
 * @author garyn
 * @date 8/05/2025 00:36
 */
@Service
public class ProfessorService implements ProfessorUseCase {

  private final ProfessorRepositoryPort professorRepositoryPort;
  private final OrcidUseCase orcidUseCase;

  private static final Logger logger = LoggerFactory.getLogger(ProfessorService.class);

  @Autowired
  public ProfessorService(
      ProfessorRepositoryPort professorRepositoryPort, OrcidUseCase orcidUseCase) {
    this.professorRepositoryPort = professorRepositoryPort;
    this.orcidUseCase = orcidUseCase;
  }

  @Override
  public List<ProfessorResponse> getAllProfessors() {
    return professorRepositoryPort.findAll().stream().map(this::mapToProfessorResponse).toList();
  }

  @Override
  public ProfessorResponse getProfessorById(String id) {
    return professorRepositoryPort.findById(id).map(this::mapToProfessorResponse).orElse(null);
  }

  @Override
  public List<ProfessorResponse> getProfessorsByFaculty(String faculty) {
    return professorRepositoryPort.findByFaculty(faculty).stream()
        .map(this::mapToProfessorResponse)
        .toList();
  }

  @Override
  public List<ProfessorResponse> getProfessorsByName(String name) {
    return professorRepositoryPort.findByName(name).stream()
        .map(this::mapToProfessorResponse)
        .toList();
  }

  @Override
  public Page<ProfessorResponse> getProfessorsByNamePageable(String name, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return professorRepositoryPort.findByName(name, pageable).map(this::mapToProfessorResponse);
  }

  @Override
  public List<ProfessorResponse> getProfessorsByCourse(String course) {
    // regex to match general course
    String regex = ".*" + Pattern.quote(course) + ".*";
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    return getAllProfessors().stream()
        .filter(
            p ->
                p.getComments().stream()
                    .anyMatch(c -> pattern.matcher(normalizeString(c.get("course"))).matches()))
        .toList();
  }

  @Override
  public Page<ProfessorResponse> getProfessorsByCoursePageable(String course, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return professorRepositoryPort.findByCourse(course, pageable).map(this::mapToProfessorResponse);
  }

  @Override
  public Page<ProfessorResponse> getAllProfessorsPageable(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return professorRepositoryPort.findAll(pageable).map(this::mapToProfessorResponse);
  }

  @Override
  public Page<ProfessorResponse> getProfessorsByFacultyPageable(String faculty, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return professorRepositoryPort.findByFaculty(faculty, pageable).map(this::mapToProfessorResponse);
  }

  private ProfessorResponse mapToProfessorResponse(Professor professor) {
    ProfessorResponse professorResponse = new ProfessorResponse();

    professorResponse.setId(professor.getId());
    professorResponse.setName(professor.getName());
    professorResponse.setFaculty(professor.getFaculty());
    professorResponse.setRatingsCount(professor.getRatingsCount());
    professorResponse.setAverageRating(professor.getAverageRating());
    professorResponse.setProfileUrl(professor.getProfileUrl());
    professorResponse.setTags(professor.getTags());
    professorResponse.setStats(professor.getStats());
    professorResponse.setComments(professor.getComments());

    try {
      Optional<String> orcidId = orcidUseCase.getOrcidId(professor.getName());
      if (orcidId.isPresent()) {
        logger.info("Getting orcid info for professor: {}", professor.getName());
        professorResponse.setOrcid(orcidUseCase.getOrcidInfo(orcidId.get()));
      } else {
        logger.info("Orcid not found for professor: {}", professor.getName());
      }

    } catch (Exception e) {
      logger.info("Error getting orcid info for professor: {}", professor.getName(), e);
    }

    return professorResponse;
  }

  private String normalizeString(String text) {
    if (text == null || text.isEmpty()) return "";
    // avoid á, é, í, ó, ú
    String newText = text.toLowerCase();

    return newText
        .replace("á", "a")
        .replace("é", "e")
        .replace("í", "i")
        .replace("ó", "o")
        .replace("ú", "u");
  }
}
