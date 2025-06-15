package unmsm.api.professors.professors.domain.ports.input;

import org.springframework.data.domain.Page;
import unmsm.api.professors.professors.application.dto.ProfessorResponse;

import java.util.List;

/**
 * @author garyn
 * @date 8/05/2025 00:38
 */
public interface ProfessorUseCase {
    List<ProfessorResponse> getAllProfessors();

    ProfessorResponse getProfessorById(String id);

    List<ProfessorResponse> getProfessorsByFaculty(String faculty);

    List<ProfessorResponse> getProfessorsByName(String name);

    Page<ProfessorResponse> getProfessorsByNamePageable(String name, int page, int size);

    List<ProfessorResponse> getProfessorsByCourse(String course);

    Page<ProfessorResponse> getProfessorsByCoursePageable(String course, int page, int size);

    Page<ProfessorResponse> getAllProfessorsPageable(int page,int size);

    Page<ProfessorResponse> getProfessorsByFacultyPageable(String faculty, int page, int size);
}
