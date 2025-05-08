package unmsm.api.professors.professors.infrastructure.persistence.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import unmsm.api.professors.professors.infrastructure.persistence.mongo.docuement.ProfessorDocument;

import java.util.List;

/**
 * @author garyn
 * @date 8/05/2025 00:54
 */
public interface ProfessorMongoRepository extends MongoRepository<ProfessorDocument, String> {

    @Query("{ 'faculty': { $regex: ?0, $options: 'i' } }")
    List<ProfessorDocument> findByFaculty(String faculty);

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<ProfessorDocument> findByName(String name);
}
