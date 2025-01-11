package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormProgressState;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm.InternshipFormService;
import mohammad.shahheydar.internshipprocessmanagement.service.Opinion.OpinionService;
import mohammad.shahheydar.internshipprocessmanagement.service.utils.UserExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;
    private final InternshipFormService internshipFormService;

    @PostMapping("universityTrainingStaff/opinion/internship-forms/{id}")
    public ResponseEntity<String> universityTrainingStaffOpinionOnInternshipForms(@PathVariable Long id, @RequestBody OpinionDto opinionDto, HttpServletRequest request) {

        Employee employee = UserExtractor.getEmployee(request);

        InternshipForm internshipForm = internshipFormService.findOriginalById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "internship form not found"));

        opinionService.employeeOpinionOnInternshipForms(opinionDto, employee, internshipForm, InternshipFormProgressState.UNIVERSITY_TRAINING_STAFF);

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @PostMapping("departmentHead/opinion/internship-forms/{id}")
    public ResponseEntity<String> departmentHeadOpinionOnInternshipForms(@PathVariable Long id, @RequestParam(required = false) Long guideTeacherId, @RequestBody OpinionDto opinionDto, HttpServletRequest request) {
        try {
            Employee employee = UserExtractor.getEmployee(request);

            InternshipForm internshipForm = internshipFormService.findOriginalById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "internship form not found"));

            opinionService.employeeOpinionOnInternshipForms(opinionDto, employee, internshipForm, InternshipFormProgressState.DEPARTMENT_HEAD , guideTeacherId);

            return ResponseEntity.status(HttpStatus.CREATED).body("created");
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return ResponseEntity.status(HttpStatus.CREATED).body("created");
        }
    }

    @PostMapping("facultyTrainingStaff/opinion/internship-forms/{id}")
    public ResponseEntity<String> facultyTrainingStaffOpinionOnInternshipForms(@PathVariable Long id, @RequestBody OpinionDto opinionDto, HttpServletRequest request) {

        Employee employee = UserExtractor.getEmployee(request);

        InternshipForm internshipForm = internshipFormService.findOriginalById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "internship form not found"));

        opinionService.employeeOpinionOnInternshipForms(opinionDto, employee, internshipForm, InternshipFormProgressState.FACULTY_TRAINING_STAFF);

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }
}
