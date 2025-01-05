package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormProgressState;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.service.Opinion.OpinionService;
import mohammad.shahheydar.internshipprocessmanagement.service.utils.UserExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;

//    todo: update internship form progress state
//    todo: make update operation and save opinion transactional
    @PostMapping("universityTrainingStaff/opinion/internship-forms/{id}")
    public ResponseEntity<String> universityTrainingStaffOpinionOnInternshipForms(@PathVariable Long id , @RequestBody OpinionDto opinionDto , HttpServletRequest request) {

        Employee employee = new Employee();
        employee.setId(UserExtractor.getEmployee(request).getId());

        InternshipForm internshipForm = new InternshipForm();
        internshipForm.setId(id);

        opinionService.employeeOpinionOnInternshipForms(opinionDto , employee , internshipForm , InternshipFormProgressState.UNIVERSITY_TRAINING_STAFF);

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @PostMapping("departmentHead/opinion/internship-forms/{id}")
    public ResponseEntity<String> departmentHeadOpinionOnInternshipForms(@PathVariable Long id , @RequestBody OpinionDto opinionDto , HttpServletRequest request) {

        Employee employee = new Employee();
        employee.setId(UserExtractor.getEmployee(request).getId());

        InternshipForm internshipForm = new InternshipForm();
        internshipForm.setId(id);

        opinionService.employeeOpinionOnInternshipForms(opinionDto , employee , internshipForm , InternshipFormProgressState.DEPARTMENT_HEAD);

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @PostMapping("facultyTrainingStaff/opinion/internship-forms/{id}")
    public ResponseEntity<String> facultyTrainingStaffOpinionOnInternshipForms(@PathVariable Long id , @RequestBody OpinionDto opinionDto , HttpServletRequest request) {

        Employee employee = new Employee();
        employee.setId(UserExtractor.getEmployee(request).getId());

        InternshipForm internshipForm = new InternshipForm();
        internshipForm.setId(id);

        opinionService.employeeOpinionOnInternshipForms(opinionDto , employee , internshipForm , InternshipFormProgressState.FACULTY_TRAINING_STAFF);

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }
}
