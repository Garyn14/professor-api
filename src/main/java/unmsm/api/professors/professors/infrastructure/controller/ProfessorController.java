package unmsm.api.professors.professors.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import unmsm.api.professors.professors.application.dto.ProfessorResponse;
import unmsm.api.professors.professors.domain.ports.input.ProfessorUseCase;

import java.util.List;

/**
 * @author garyn
 * @date 8/05/2025 00:39
 */
@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private final ProfessorUseCase professorUseCase;
    private static final Logger logger = LoggerFactory.getLogger(ProfessorController.class);

    @Autowired
    public ProfessorController(ProfessorUseCase professorUseCase) {
        this.professorUseCase = professorUseCase;
    }

    @GetMapping("/health")
    public String hello() {
        return "health";
    }

    @GetMapping
    public List<ProfessorResponse> getAllProfessors() {
        logger.info("Getting all professors");
        logger.debug("number of professors: {}", professorUseCase.getAllProfessors().size());
        return professorUseCase.getAllProfessors();
    }

    @GetMapping("/pageable")
    public Page<ProfessorResponse> getAllProfessorsPageable(int page, int size) {
        return professorUseCase.getAllProfessorsPageable(page, size);
    }

    /*
    @GetMapping("/{id}")
    public ProfessorResponse getProfessorById(@PathVariable String id) {
        logger.info("Getting professor by id: {}", id);
        return professorUseCase.getProfessorById(id);
    }*/

    @GetMapping("/faculty/{faculty}")
    public List<ProfessorResponse> getProfessorsByFaculty(@PathVariable String faculty) {
        logger.info("Getting professors by faculty: {}", faculty);
        return professorUseCase.getProfessorsByFaculty(faculty);
    }

    @GetMapping("/faculty-pageable")
    public Page<ProfessorResponse> getProfessorsByFacultyPageable(@RequestParam String faculty, int page, int size) {
        logger.info("Getting professors by faculty: {} with pagination", faculty);
        return professorUseCase.getProfessorsByFacultyPageable(faculty, page, size);
    }

    @GetMapping("/name/{name}")
    public List<ProfessorResponse> getProfessorsByName(@PathVariable String name) {
        logger.info("Getting professors by name: {}", name);
        return professorUseCase.getProfessorsByName(name);
    }

    @GetMapping("/name-pageable")
    public Page<ProfessorResponse> getProfessorsByNamePageable(@RequestParam String name, int page, int size) {
        logger.info("Getting professors by name: {} with pagination", name);
        return professorUseCase.getProfessorsByNamePageable(name, page, size);
    }

    @GetMapping("/course/{course}")
    public List<ProfessorResponse> getProfessorsByCourse(@PathVariable String course) {
        logger.info("Getting professors by course: {}", course);
        return professorUseCase.getProfessorsByCourse(course);
    }

    @GetMapping("/course-pageable")
    public Page<ProfessorResponse> getProfessorsByCoursePageable(@RequestParam String course, int page, int size) {
        logger.info("Getting professors by course: {} with pagination", course);
        return professorUseCase.getProfessorsByCoursePageable(course, page, size);
    }
}
