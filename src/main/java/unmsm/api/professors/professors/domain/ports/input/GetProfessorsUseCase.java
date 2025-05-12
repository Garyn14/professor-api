package unmsm.api.professors.professors.domain.ports.input;

import unmsm.api.professors.professors.application.dto.ProfessorResponse;

import java.util.List;

/**
 * @author garyn
 * @date 8/05/2025 00:38
 */
public interface GetProfessorsUseCase {
    List<ProfessorResponse> getAllProfessors();

    ProfessorResponse getProfessorById(String id);

    List<ProfessorResponse> getProfessorsByFaculty(String faculty);

    List<ProfessorResponse> getProfessorsByName(String name);

    List<ProfessorResponse> getProfessorsByCourse(String course);
}
