package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormDto;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormListDto;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormProgressState;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormState;
import mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm.InternshipFormService;
import mohammad.shahheydar.internshipprocessmanagement.service.utils.UserExtractor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class InternshipFormController {

    private final InternshipFormService internshipFormService;

//    @GetMapping("all")
//    public ResponseEntity<Page<InternshipFormListDto>> getAll(@RequestParam(required = false , defaultValue = "0") int page , @RequestParam(required = false , defaultValue = "8") int pageSize) {
//        return ResponseEntity.ok(internshipFormService.findAll(Pageable.ofSize(20).withPage(page)));
//    }

    @GetMapping("student/internship-forms")
    public ResponseEntity<Page<InternshipFormListDto>> getAllUniversityTrainingStaffForm(HttpServletRequest request , @RequestParam(required = false , defaultValue = "0") int page , @RequestParam(required = false , defaultValue = "8") int pageSize) {
        return ResponseEntity.ok(internshipFormService.findAllByStudentId(UserExtractor.getStudent(request).getId(), Pageable.ofSize(pageSize).withPage(page)));
    }

    @GetMapping("universityTrainingStaff/internship-forms")
    public ResponseEntity<Page<InternshipFormListDto>> getAllUniversityTrainingStaffForm(@RequestParam(required = false , defaultValue = "0") int page , @RequestParam(required = false , defaultValue = "8") int pageSize) {
        return ResponseEntity.ok(internshipFormService.findAllByProgressState(Pageable.ofSize(pageSize).withPage(page), null));
    }

    @GetMapping("departmentHead/internship-forms")
    public ResponseEntity<Page<InternshipFormListDto>> getAllDepartmentHeadForm(@RequestParam(required = false , defaultValue = "0") int page , @RequestParam(required = false , defaultValue = "8") int pageSize) {
        return ResponseEntity.ok(internshipFormService.findAllByProgressState(Pageable.ofSize(pageSize).withPage(page), InternshipFormProgressState.UNIVERSITY_TRAINING_STAFF));
    }

    @GetMapping("facultyTrainingStaff/internship-forms")
    public ResponseEntity<Page<InternshipFormListDto>> getAllFacultyTrainingStaffForm(@RequestParam(required = false , defaultValue = "0") int page , @RequestParam(required = false , defaultValue = "8") int pageSize) {
        return ResponseEntity.ok(internshipFormService.findAllByProgressState(Pageable.ofSize(pageSize).withPage(page), InternshipFormProgressState.DEPARTMENT_HEAD));
    }

    @GetMapping("internship-forms/{id}")
    public ResponseEntity<InternshipFormDto> get(@PathVariable Long id) {
        var internshipForm = internshipFormService.findById(id).orElseThrow(NoSuchElementException::new);
        return ResponseEntity.ok(internshipForm);
    }


    @PostMapping("student/internship-forms/save")
    public ResponseEntity<String> save(@Valid @RequestBody InternshipFormDto internshipFormDto, HttpServletRequest request) {
        if (internshipFormService.studentInProgressFormCount(UserExtractor.getStudent(request).getId() , InternshipFormState.IN_PROGRESS) > 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "You already have an in progress form");

        internshipFormService.save(internshipFormDto, UserExtractor.getStudent(request).getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
