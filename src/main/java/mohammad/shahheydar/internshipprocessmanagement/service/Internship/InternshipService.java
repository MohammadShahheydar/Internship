package mohammad.shahheydar.internshipprocessmanagement.service.Internship;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.*;
import mohammad.shahheydar.internshipprocessmanagement.mapper.InternshipMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.InternshipRepository;
import mohammad.shahheydar.internshipprocessmanagement.service.PresenceAndAbsence.PresenceAndAbsenceService;
import mohammad.shahheydar.internshipprocessmanagement.service.weeklyReport.WeeklyReportService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final InternshipMapper internshipMapper;
    private final WeeklyReportService weeklyReportService;
    private final PresenceAndAbsenceService presenceAndAbsenceService;

    public Internship findById(Long id) {
        return internshipRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "internship not found"));
    }

    public Internship save(Internship internship) {
        return internshipRepository.save(internship);
    }

    public void update(Internship internship) {
        internshipRepository.save(internship);
    }

    public Optional<Internship> findByStudent(Student student) {
        return internshipRepository.findByStudent(student);
    }

    public Optional<InternshipDto> findDtoByStudent(Student student) {
        return internshipRepository.findByStudent(student).map(internshipMapper::toDto);
    }

    public List<InternshipDto> findDtoBySupervisor(Employee supervisor) {
        return internshipMapper.toDtoList(internshipRepository.findBySupervisor(supervisor));
    }

    public List<InternshipDto> findDtoByGuideTeacher(Employee guideTeacher) {
        return internshipMapper.toDtoList(internshipRepository.findByGuideTeacher(guideTeacher));
    }

    public void saveWeeklyReport(Student student , Long internshipId, WeeklyReport weeklyReport) {
        if (!studentHasPermission(student , internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN , "student access denied");
        Internship internship = findById(internshipId);
        WeeklyReport savedWeeklyReport = weeklyReportService.save(weeklyReport);
        internship.addWeeklyReport(savedWeeklyReport);
        internshipRepository.save(internship);
    }

    public void updateWeeklyReport(Student student , Long internshipId, WeeklyReport weeklyReport , Long reportId) {
        if (!studentHasPermission(student , internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN , "student access denied");
        weeklyReportService.update(weeklyReport , reportId);
    }

    public void savePresenceAndAbsence(Student student , Long internshipId, PresenceAndAbsence presenceAndAbsence) {
        if (!studentHasPermission(student , internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN , "student access denied");
        Internship internship = findById(internshipId);
        PresenceAndAbsence savedPresenceAndAbsence = presenceAndAbsenceService.save(presenceAndAbsence);
        internship.addPresenceAndAbsences(savedPresenceAndAbsence);
        internshipRepository.save(internship);
    }

    public void updatePresenceAndAbsence(Student student , Long internshipId, PresenceAndAbsence presenceAndAbsence , Long reportId) {
        if (!studentHasPermission(student , internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN , "student access denied");
        presenceAndAbsenceService.update(presenceAndAbsence , reportId);
    }

    public void supervisorConfirmWeeklyReport(Employee supervisor, Long internshipId, Long reportId , Boolean confirm) {
        if (!supervisorHasPermission(supervisor , internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN , "supervisor access denied");

        weeklyReportService.supervisorConfirm(reportId , confirm);
    }

    public void supervisorConfirmPresenceAndAbsence(Employee supervisor, Long internshipId, Long reportId , Boolean confirm) {
        if (!supervisorHasPermission(supervisor , internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN , "supervisor access denied");

        presenceAndAbsenceService.supervisorConfirm(reportId , confirm);
    }

    public void guideTeacherConfirmWeeklyReport(Employee guideTeacher, Long internshipId, Long reportId , Boolean confirm) {
        if (!guideTeacherHasPermission(guideTeacher , internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN , "guideTeacher access denied");

        weeklyReportService.guideTeacherConfirm(reportId , confirm);
    }

    public void guideTeacherConfirmPresenceAndAbsence(Employee guideTeacher, Long internshipId, Long reportId , Boolean confirm) {
        if (!guideTeacherHasPermission(guideTeacher , internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN , "guideTeacher access denied");

        presenceAndAbsenceService.guideTeacherConfirm(reportId , confirm);
    }

    private boolean supervisorHasPermission(Employee supervisor , Long internshipId) {
        Internship internship = findById(internshipId);
        return internship.getSupervisor().equals(supervisor);
    }

    private boolean studentHasPermission(Student student , Long internshipId) {
        Internship internship = findById(internshipId);
        return internship.getStudent().equals(student);
    }

    private boolean guideTeacherHasPermission(Employee guideTeacher , Long internshipId) {
        Internship internship = findById(internshipId);
        return internship.getGuideTeacher().equals(guideTeacher);
    }
}
