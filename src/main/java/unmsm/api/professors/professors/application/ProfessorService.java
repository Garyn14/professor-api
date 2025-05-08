package unmsm.api.professors.professors.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unmsm.api.professors.professors.application.dto.ProfessorResponse;
import unmsm.api.professors.professors.domain.model.Professor;
import unmsm.api.professors.professors.domain.ports.input.GetProfessorsUseCase;
import unmsm.api.professors.professors.domain.ports.output.ProfessorRepositoryPort;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author garyn
 * @date 8/05/2025 00:36
 */
@Service
public class ProfessorService implements GetProfessorsUseCase {

    private final ProfessorRepositoryPort professorRepositoryPort;

    @Autowired
    public ProfessorService(ProfessorRepositoryPort professorRepositoryPort) {
        this.professorRepositoryPort = professorRepositoryPort;
    }

    @Override
    public List<ProfessorResponse> getAllProfessors() {
        return professorRepositoryPort.findAll()
                .stream()
                .map(this::mapToProfessorResponse)
                .toList();
    }

    @Override
    public ProfessorResponse getProfessorById(String id) {
        return professorRepositoryPort.findById(id)
                .map(this::mapToProfessorResponse)
                .orElse(null);
    }

    @Override
    public List<ProfessorResponse> getProfessorsByFaculty(String faculty) {
        return professorRepositoryPort.findByFaculty(faculty)
                .stream()
                .map(this::mapToProfessorResponse)
                .toList();
    }

    @Override
    public List<ProfessorResponse> getProfessorsByName(String name) {
        return professorRepositoryPort.findByName(name)
                .stream()
                .map(this::mapToProfessorResponse)
                .toList();
    }

    @Override
    public List<ProfessorResponse> getProfessorsByCourse(String course) {
        // regex to match general course
        String regex = ".*" + Pattern.quote(course) + ".*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        return getAllProfessors()
                .stream()
                .filter(p -> p.getComments()
                        .stream()
                        .anyMatch(c -> pattern.matcher(normalizeString(c.get("course"))).matches()))
                .toList();
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

        return professorResponse;
    }

    private String normalizeString(String text) {
        if (text == null || text.isEmpty()) return "";
        // avoid á, é, í, ó, ú
        String newText = text.toLowerCase();

        return newText.replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u");

    }
}
