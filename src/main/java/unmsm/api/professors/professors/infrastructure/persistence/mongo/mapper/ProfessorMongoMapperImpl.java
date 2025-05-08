package unmsm.api.professors.professors.infrastructure.persistence.mongo.mapper;

import org.springframework.stereotype.Component;
import unmsm.api.professors.professors.domain.model.Professor;
import unmsm.api.professors.professors.infrastructure.persistence.mongo.docuement.ProfessorDocument;

/**
 * @author garyn
 * @date 8/05/2025 01:11
 */
@Component
public class ProfessorMongoMapperImpl implements ProfessorMongoMapper{
    @Override
    public Professor toModel(ProfessorDocument professorDocument) {
        if (professorDocument == null) {
            return null;
        }

        Professor professor = new Professor();

        professor.setId(professorDocument.getId());
        professor.setName(professorDocument.getName());
        professor.setFaculty(professorDocument.getFaculty());
        professor.setRatingsCount(professorDocument.getRatingsCount());
        professor.setAverageRating(professorDocument.getAverageRating());
        professor.setProfileUrl(professorDocument.getProfileUrl());
        professor.setTags(professorDocument.getTags());
        professor.setStats(professorDocument.getStats());
        professor.setComments(professorDocument.getComments());

        return professor;
    }

    @Override
    public ProfessorDocument toDocument(Professor professor) {
        if (professor == null) {
            return null;
        }

        ProfessorDocument professorDocument = new ProfessorDocument();

        professorDocument.setId(professor.getId());
        professorDocument.setName(professor.getName());
        professorDocument.setFaculty(professor.getFaculty());
        professorDocument.setRatingsCount(professor.getRatingsCount());
        professorDocument.setAverageRating(professor.getAverageRating());
        professorDocument.setProfileUrl(professor.getProfileUrl());
        professorDocument.setTags(professor.getTags());
        professorDocument.setStats(professor.getStats());
        professorDocument.setComments(professor.getComments());

        return professorDocument;
    }
}
