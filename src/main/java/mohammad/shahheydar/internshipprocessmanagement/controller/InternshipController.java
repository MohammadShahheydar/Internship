package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.PresenceAndAbsence;
import mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipDto;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.model.WeeklyReportDto;
import mohammad.shahheydar.internshipprocessmanagement.service.Internship.InternshipService;
import mohammad.shahheydar.internshipprocessmanagement.service.file.FileService;
import mohammad.shahheydar.internshipprocessmanagement.service.utils.UserExtractor;
import mohammad.shahheydar.internshipprocessmanagement.service.weeklyReport.WeeklyReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;
    private final WeeklyReportService weeklyReportService;
    private final FileService fileService;

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

    @GetMapping("guideTeacher/internship")
    public ResponseEntity<List<InternshipDto>> getGuideTeacherInternship(HttpServletRequest request) {
        return ResponseEntity.ok(
                internshipService.findDtoByGuideTeacher(UserExtractor.getEmployee(request)));
    }

    @GetMapping("supervisor/internship")
    public ResponseEntity<List<InternshipDto>> getSupervisorInternship(HttpServletRequest request) {
        return ResponseEntity.ok(
                internshipService.findDtoBySupervisor(UserExtractor.getEmployee(request)));
    }

    @PostMapping(value = "student/internship/{id}/weekly-report")
    public ResponseEntity<String> addWeeklyReport(
            @PathVariable Long id,
            @RequestBody @Valid WeeklyReportDto weeklyReport,
            HttpServletRequest request
    ) {
        internshipService.saveWeeklyReport(UserExtractor.getStudent(request), id, weeklyReport);
        return ResponseEntity.status(HttpStatus.CREATED).body("report created");
    }

    @PutMapping(value = "student/internship/{id}/weekly-report/{reportId}/update")
    public ResponseEntity<String> updateWeeklyReport(
            @PathVariable Long id,
            @PathVariable Long reportId,
            @RequestBody @Valid WeeklyReportDto weeklyReport,
            HttpServletRequest request
    ) {
        internshipService.updateWeeklyReport(UserExtractor.getStudent(request), id, weeklyReport, reportId);
        return ResponseEntity.status(HttpStatus.OK).body("report updated");
    }

    @PostMapping("student/internship/{id}/presence-and-absence-report")
    public ResponseEntity<String> addPresenceAndAbsence(@PathVariable Long id, @RequestBody @Valid PresenceAndAbsence presenceAndAbsence, HttpServletRequest request) {
        internshipService.savePresenceAndAbsence(UserExtractor.getStudent(request), id, presenceAndAbsence);
        return ResponseEntity.status(HttpStatus.CREATED).body("report created");
    }

    @PutMapping("student/internship/{id}/presence-and-absence-report/{reportId}/update")
    public ResponseEntity<String> updatePresenceAndAbsence(@PathVariable Long id, @PathVariable Long reportId, @RequestBody @Valid PresenceAndAbsence presenceAndAbsence, HttpServletRequest request) {
        internshipService.updatePresenceAndAbsence(UserExtractor.getStudent(request), id, presenceAndAbsence, reportId);
        return ResponseEntity.status(HttpStatus.OK).body("report updated");
    }

    @PostMapping("supervisor/internship/{id}/confirm-weekly-report/{reportId}")
    public ResponseEntity<String> supervisorConfirmWeeklyReport(@PathVariable Long id, @PathVariable Long reportId, @RequestBody @Valid OpinionDto confirm, HttpServletRequest request) {
        internshipService.supervisorConfirmWeeklyReport(UserExtractor.getEmployee(request), id, reportId, confirm);
        return ResponseEntity.status(HttpStatus.CREATED).body("confirmed");
    }

    @PostMapping("supervisor/internship/{id}/confirm-presence-and-absence-report/{reportId}")
    public ResponseEntity<String> supervisorConfirmPresenceAndAbsence(@PathVariable Long id, @PathVariable Long reportId, @RequestBody @Valid OpinionDto confirm, HttpServletRequest request) {
        internshipService.supervisorConfirmPresenceAndAbsence(UserExtractor.getEmployee(request), id, reportId, confirm);
        return ResponseEntity.status(HttpStatus.CREATED).body("confirmed");
    }

    @PostMapping("guideTeacher/internship/{id}/confirm-weekly-report/{reportId}")
    public ResponseEntity<String> guideTeacherConfirmWeeklyReport(@PathVariable Long id, @PathVariable Long reportId, @RequestBody @Valid OpinionDto confirm, HttpServletRequest request) {
        internshipService.guideTeacherConfirmWeeklyReport(UserExtractor.getEmployee(request), id, reportId, confirm);
        return ResponseEntity.status(HttpStatus.CREATED).body("confirmed");
    }

    @PostMapping("guideTeacher/internship/{id}/confirm-presence-and-absence-report/{reportId}")
    public ResponseEntity<String> guideTeacherConfirmPresenceAndAbsence(@PathVariable Long id, @PathVariable Long reportId, @RequestBody @Valid OpinionDto confirm, HttpServletRequest request) {
        internshipService.guideTeacherConfirmPresenceAndAbsence(UserExtractor.getEmployee(request), id, reportId, confirm);
        return ResponseEntity.status(HttpStatus.CREATED).body("confirmed");
    }
}
