package mohammad.shahheydar.internshipprocessmanagement.service.Internship;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.*;
import mohammad.shahheydar.internshipprocessmanagement.mapper.InternshipMapper;
import mohammad.shahheydar.internshipprocessmanagement.mapper.OpinionMapper;
import mohammad.shahheydar.internshipprocessmanagement.mapper.WeeklyReportMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipDto;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipState;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.model.WeeklyReportDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.InternshipRepository;
import mohammad.shahheydar.internshipprocessmanagement.repository.OpinionRepository;
import mohammad.shahheydar.internshipprocessmanagement.service.Opinion.OpinionService;
import mohammad.shahheydar.internshipprocessmanagement.service.PresenceAndAbsence.PresenceAndAbsenceService;
import mohammad.shahheydar.internshipprocessmanagement.service.file.FileService;
import mohammad.shahheydar.internshipprocessmanagement.service.weeklyReport.WeeklyReportService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final InternshipMapper internshipMapper;
    private final WeeklyReportService weeklyReportService;
    private final PresenceAndAbsenceService presenceAndAbsenceService;
    private final WeeklyReportMapper weeklyReportMapper;
    private final FileService fileService;
    private final OpinionRepository opinionRepository;
    private final OpinionMapper opinionMapper;

    public Internship findById(Long id) {
        return internshipRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "internship not found"));
    }

    public Internship save(Internship internship) {
        return internshipRepository.save(internship);
    }

    public void update(Internship internship) {
        internshipRepository.save(internship);
    }

    public Internship findByStudent(Student student) {
        return internshipRepository.findByStudent(student).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST , "internship not found"));
    }

    public Optional<InternshipDto> findDtoByStudent(Student student) {
        return internshipRepository.findByStudent(student).map(internshipMapper::toDto);
    }

    public List<InternshipDto> findDtoBySupervisor(Employee supervisor) {
        return internshipMapper.toDtoList(internshipRepository.findBySupervisor(supervisor));
    }

    public List<InternshipDto> findDtoByGuideTeacher(Employee guideTeacher , InternshipState state) {
        return internshipMapper.toDtoList(internshipRepository.findByGuideTeacherAndState(guideTeacher , state));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void guideTeacherConfirm(OpinionDto opinionDto , Employee guideTeacher , Long internshipId) {
        Internship internship = findById(internshipId);
        if (!studentHasPermission(internship.getStudent(), internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "student access denied");
        Opinion opinion = opinionMapper.toEntity(opinionDto);
        opinion.setOpinionTarget(internship);
        opinion.setUser(guideTeacher);
        opinionRepository.save(opinion);
        if (opinion.getConfirm())
            internship.setState(InternshipState.COMPLETE);
        else
            internship.setState(InternshipState.IN_PROGRESS);
        save(internship);
    }

    public void saveWeeklyReport(Student student, Long internshipId, WeeklyReportDto weeklyReport) {
        if (!studentHasPermission(student, internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "student access denied");
        Internship internship = findById(internshipId);
        WeeklyReport savedWeeklyReport = weeklyReportService.save(weeklyReportMapper.toEntity(weeklyReport));
        internship.addWeeklyReport(savedWeeklyReport);
        internshipRepository.save(internship);
    }

    public void updateWeeklyReport(Student student, Long internshipId, WeeklyReportDto weeklyReport, Long reportId) {
        if (!studentHasPermission(student, internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "student access denied");
        weeklyReportService.update(weeklyReportMapper.toEntity(weeklyReport), reportId);
    }

    public void savePresenceAndAbsence(Student student, Long internshipId, PresenceAndAbsence presenceAndAbsence) {
        if (!studentHasPermission(student, internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "student access denied");
        Internship internship = findById(internshipId);
        PresenceAndAbsence savedPresenceAndAbsence = presenceAndAbsenceService.save(presenceAndAbsence);
        internship.addPresenceAndAbsences(savedPresenceAndAbsence);
        internshipRepository.save(internship);
    }

    public void updatePresenceAndAbsence(Student student, Long internshipId, PresenceAndAbsence presenceAndAbsence, Long reportId) {
        if (!studentHasPermission(student, internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "student access denied");
        presenceAndAbsenceService.update(presenceAndAbsence, reportId);
    }

    public void supervisorConfirmWeeklyReport(Employee supervisor, Long internshipId, Long reportId, OpinionDto confirm) {
        if (!supervisorHasPermission(supervisor, internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "supervisor access denied");

        weeklyReportService.supervisorConfirm(reportId, supervisor, confirm);
    }

    public void supervisorConfirmPresenceAndAbsence(Employee supervisor, Long internshipId, Long reportId, OpinionDto confirm) {
        if (!supervisorHasPermission(supervisor, internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "supervisor access denied");

        presenceAndAbsenceService.supervisorConfirm(reportId, supervisor ,  confirm);
    }

    public void guideTeacherConfirmWeeklyReport(Employee guideTeacher, Long internshipId, Long reportId, OpinionDto confirm) {
        if (!guideTeacherHasPermission(guideTeacher, internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "guideTeacher access denied");

        weeklyReportService.guideTeacherConfirm(reportId, guideTeacher, confirm);
    }

    public void guideTeacherConfirmPresenceAndAbsence(Employee guideTeacher, Long internshipId, Long reportId, OpinionDto confirm) {
        if (!guideTeacherHasPermission(guideTeacher, internshipId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "guideTeacher access denied");

        presenceAndAbsenceService.guideTeacherConfirm(reportId, guideTeacher , confirm);
    }

    public void studentAskForInspect(Student student) {
        Internship internship = internshipRepository.findByStudent(student).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "student doesn't has any internship"));
        if (!studentHasPermission(student , internship.getId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "student access denied");
        internship.setState(InternshipState.PENDING);
        save(internship);
    }

    public void studentUploadFinalReport(Student student, MultipartFile report) throws IOException {
        Internship internship = findByStudent(student);
        String studentFinalReport = fileService.saveFile(report , "studentFinalReport");
        internship.setStudentFinalReport(studentFinalReport);
        save(internship);
    }

    private boolean supervisorHasPermission(Employee supervisor, Long internshipId) {
        Internship internship = findById(internshipId);
        return internship.getSupervisor().equals(supervisor);
    }

    private boolean studentHasPermission(Student student, Long internshipId) {
        Internship internship = findById(internshipId);
        return internship.getStudent().equals(student);
    }

    private boolean guideTeacherHasPermission(Employee guideTeacher, Long internshipId) {
        Internship internship = findById(internshipId);
        return internship.getGuideTeacher().equals(guideTeacher);
    }
}
