package unmsm.api.professors.professors.domain.ports.output;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Professor> findByFaculty(String faculty, Pageable pageable);

    List<Professor> findByName(String name);

    Page<Professor> findByName(String name, Pageable pageable);

    Page<Professor> findByCourse(String course, Pageable pageable);

    Page<Professor> findAll(Pageable pageable);

}
