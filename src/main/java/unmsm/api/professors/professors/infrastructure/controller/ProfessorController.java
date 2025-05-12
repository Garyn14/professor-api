package unmsm.api.professors.professors.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unmsm.api.professors.professors.application.dto.ProfessorResponse;
import unmsm.api.professors.professors.domain.ports.input.GetProfessorsUseCase;

import java.util.List;

/**
 * @author garyn
 * @date 8/05/2025 00:39
 */
@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private final GetProfessorsUseCase getProfessorsUseCase;
    private static final Logger logger = LoggerFactory.getLogger(ProfessorController.class);

    @Autowired
    public ProfessorController(GetProfessorsUseCase getProfessorsUseCase) {
        this.getProfessorsUseCase = getProfessorsUseCase;
    }

    @GetMapping
    public List<ProfessorResponse> getAllProfessors() {
        logger.info("Getting all professors");
        logger.debug("number of professors: {}", getProfessorsUseCase.getAllProfessors().size());
        return getProfessorsUseCase.getAllProfessors();
    }

    @GetMapping("/{id}")
    public ProfessorResponse getProfessorById(@PathVariable String id) {
        logger.info("Getting professor by id: {}", id);
        return getProfessorsUseCase.getProfessorById(id);
    }

    @GetMapping("/faculty/{faculty}")
    public List<ProfessorResponse> getProfessorsByFaculty(@PathVariable String faculty) {
        logger.info("Getting professors by faculty: {}", faculty);
        return getProfessorsUseCase.getProfessorsByFaculty(faculty);
    }

    @GetMapping("/name/{name}")
    public List<ProfessorResponse> getProfessorsByName(@PathVariable String name) {
        logger.info("Getting professors by name: {}", name);
        return getProfessorsUseCase.getProfessorsByName(name);
    }

    @GetMapping("/course/{course}")
    public List<ProfessorResponse> getProfessorsByCourse(@PathVariable String course) {
        logger.info("Getting professors by course: {}", course);
        return getProfessorsUseCase.getProfessorsByCourse(course);
    }
}
