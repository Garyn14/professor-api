package unmsm.api.professors.professors.domain.ports.output;

import unmsm.api.professors.professors.domain.model.Professor;

import java.util.List;
import java.util.Optional;

/**
 * @author garyn
 * @date 8/05/2025 00:37
 */
public interface ProfessorRepositoryPort {
    List<Professor> findAll();
    Optional<Professor> findById(String id);
    List<Professor> findByFaculty(String faculty);
    List<Professor> findByName(String name);
}
