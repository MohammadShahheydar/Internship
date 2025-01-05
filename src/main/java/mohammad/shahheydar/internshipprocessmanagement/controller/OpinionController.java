package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinioner;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipProgressState;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.service.Opinion.OpinionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;

//    todo: update internship form progress state
//    todo: make update operation and save opinion transactional
    @PostMapping("universityTrainingStaff/opinion/internship-forms/{id}")
    public OpinionDto universityTrainingStaffOpinionOnInternshipForms(@PathVariable Long id , @RequestBody OpinionDto opinionDto , HttpServletRequest request) {

        Employee employee = new Employee();
//        todo: context holder (spring security ?!?!)
        employee.setId(((Employee) request.getAttribute("employee")).getId());

        InternshipForm internshipForm = new InternshipForm();
        internshipForm.setId(id);

        return opinionService.universityTrainingStaffOpinionOnInternshipForms(opinionDto , employee , internshipForm , InternshipProgressState.UNIVERSITY_TRAINING_STAFF);
    }
}
