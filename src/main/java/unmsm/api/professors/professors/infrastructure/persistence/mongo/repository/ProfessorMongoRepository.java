package unmsm.api.professors.professors.infrastructure.persistence.mongo.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import unmsm.api.professors.professors.infrastructure.persistence.mongo.docuement.ProfessorDocument;

/**
 * @author garyn
 * @date 8/05/2025 00:54
 */
public interface ProfessorMongoRepository extends MongoRepository<ProfessorDocument, String> {

  @Query("{ 'faculty': { $regex: ?0, $options: 'i' } }")
  List<ProfessorDocument> findByFaculty(String faculty);

  @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
  List<ProfessorDocument> findByName(String name);

  @Query("{ 'faculty': { $regex: ?0, $options: 'i' } }")
  Page<ProfessorDocument> findByFaculty(String faculty, Pageable pageable);

  @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
  Page<ProfessorDocument> findByName(String name, Pageable pageable);

  @Query("{ 'comments.course': { $regex: ?0, $options: 'i' } }")
  Page<ProfessorDocument> findByCourse(String course, Pageable pageable);
}
