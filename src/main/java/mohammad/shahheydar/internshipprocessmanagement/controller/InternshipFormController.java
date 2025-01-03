package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormDto;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormListDto;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipProgressState;
import mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm.InternshipFormService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class InternshipFormController {

    private final InternshipFormService internshipFormService;

    @GetMapping("all")
    public ResponseEntity<Page<InternshipFormListDto>> getAll() {
        return ResponseEntity.ok(internshipFormService.findAll(Pageable.ofSize(20)));
    }

    @GetMapping("universityTrainingStaff/internship-forms")
    public ResponseEntity<Page<InternshipFormListDto>> getAllUniversityTrainingStaffForm() {
        return ResponseEntity.ok(internshipFormService.findAllByProgressState(Pageable.ofSize(20) , InternshipProgressState.UNIVERSITY_TRAINING_STAFF));
    }

    @GetMapping("departmentHead/internship-forms")
    public ResponseEntity<Page<InternshipFormListDto>> getAllDepartmentHeadForm() {
        return ResponseEntity.ok(internshipFormService.findAllByProgressState(Pageable.ofSize(20) , InternshipProgressState.DEPARTMENT_HEAD));
    }

    @GetMapping("facultyTrainingStaff/internship-forms")
    public ResponseEntity<Page<InternshipFormListDto>> getAllFacultyTrainingStaffForm() {
        return ResponseEntity.ok(internshipFormService.findAllByProgressState(Pageable.ofSize(20) , InternshipProgressState.FACULTY_TRAINING_STAFF));
    }

    @GetMapping("internship-forms/{id}")
    public ResponseEntity<InternshipFormDto> get(@PathVariable Long id) {
        var internshipForm = internshipFormService.findById(id).orElseThrow(NoSuchElementException::new);
        return ResponseEntity.ok(internshipForm);
    }


    @PostMapping("student/internship-forms/save")
    public ResponseEntity<Void> save(@Valid @RequestBody InternshipFormDto internshipFormDto , HttpServletRequest request) {
        internshipFormService.save(internshipFormDto , ((Student) request.getAttribute("student")).getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
