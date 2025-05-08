package unmsm.api.professors.professors.infrastructure.persistence.mongo.mapper;

import unmsm.api.professors.professors.domain.model.Professor;
import unmsm.api.professors.professors.infrastructure.persistence.mongo.docuement.ProfessorDocument;

/**
 * @author garyn
 * @date 8/05/2025 00:54
 */
public interface ProfessorMongoMapper {
    Professor toModel(ProfessorDocument professorDocument);
    ProfessorDocument toDocument(Professor professor);
}
