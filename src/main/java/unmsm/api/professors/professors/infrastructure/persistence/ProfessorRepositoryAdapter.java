package unmsm.api.professors.professors.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import unmsm.api.professors.professors.domain.model.Professor;
import unmsm.api.professors.professors.domain.ports.output.ProfessorRepositoryPort;
import unmsm.api.professors.professors.infrastructure.persistence.mongo.mapper.ProfessorMongoMapper;
import unmsm.api.professors.professors.infrastructure.persistence.mongo.repository.ProfessorMongoRepository;

/**
 * @author garyn
 * @date 8/05/2025 00:40
 */
@Repository
public class ProfessorRepositoryAdapter implements ProfessorRepositoryPort {

  private final ProfessorMongoRepository professorMongoRepository;
  private final ProfessorMongoMapper professorMongoMapper;

  @Autowired
  public ProfessorRepositoryAdapter(
      ProfessorMongoRepository professorMongoRepository,
      ProfessorMongoMapper professorMongoMapper) {
    this.professorMongoRepository = professorMongoRepository;
    this.professorMongoMapper = professorMongoMapper;
  }

  @Override
  public List<Professor> findAll() {
    return professorMongoRepository.findAll().stream().map(professorMongoMapper::toModel).toList();
  }

  @Override
  public Optional<Professor> findById(String id) {
    return professorMongoRepository.findById(id).map(professorMongoMapper::toModel);
  }

  @Override
  public List<Professor> findByFaculty(String faculty) {
    return professorMongoRepository.findByFaculty(faculty).stream()
        .map(professorMongoMapper::toModel)
        .toList();
  }

  @Override
  public Page<Professor> findByFaculty(String faculty, Pageable pageable) {
    return professorMongoRepository
        .findByFaculty(faculty, pageable)
        .map(professorMongoMapper::toModel);
  }

  @Override
  public List<Professor> findByName(String name) {
    return professorMongoRepository.findByName(name).stream()
        .map(professorMongoMapper::toModel)
        .toList();
  }

  @Override
  public Page<Professor> findByName(String name, Pageable pageable) {
    return professorMongoRepository.findByName(name, pageable).map(professorMongoMapper::toModel);
  }

  @Override
  public Page<Professor> findByCourse(String course, Pageable pageable) {
    return professorMongoRepository
        .findByCourse(course, pageable)
        .map(professorMongoMapper::toModel);
  }

  @Override
  public Page<Professor> findAll(Pageable pageable) {
    return professorMongoRepository.findAll(pageable).map(professorMongoMapper::toModel);
  }
}
