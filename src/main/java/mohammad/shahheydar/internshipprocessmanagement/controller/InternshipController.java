package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.PresenceAndAbsence;
import mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipDto;
import mohammad.shahheydar.internshipprocessmanagement.service.Internship.InternshipService;
import mohammad.shahheydar.internshipprocessmanagement.service.utils.UserExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;

    @GetMapping("student/internship")
    public ResponseEntity<InternshipDto> getStudentInternship(HttpServletRequest request) {
        return ResponseEntity.ok(
                internshipService.findDtoByStudent(UserExtractor.getStudent(request))
                        .orElseThrow(
                                () -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "student doesn't have any internship"
                                )
                        ));
    }

    @PostMapping("student/internship/{id}/weekly-report")
    public ResponseEntity<String> addWeeklyReport(@PathVariable Long id , @RequestBody @Valid WeeklyReport weeklyReport, HttpServletRequest request) {
        internshipService.saveWeeklyReport(id , weeklyReport);
        return ResponseEntity.ok("report created");
    }

    @PostMapping("student/internship/{id}/presence-and-absence-report")
    public ResponseEntity<String> addWeeklyReport(@PathVariable Long id , @RequestBody @Valid PresenceAndAbsence presenceAndAbsence, HttpServletRequest request) {
        internshipService.savePresenceAndAbsence(id , presenceAndAbsence);
        return ResponseEntity.ok("report created");
    }

    @GetMapping("supervisor/internship")
    public ResponseEntity<List<InternshipDto>> getSupervisorInternship(HttpServletRequest request) {
        return ResponseEntity.ok(
                internshipService.findDtoBySupervisor(UserExtractor.getEmployee(request)));
    }
}
