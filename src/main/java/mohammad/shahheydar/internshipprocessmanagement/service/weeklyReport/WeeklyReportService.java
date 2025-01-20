package mohammad.shahheydar.internshipprocessmanagement.service.weeklyReport;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;
import mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport;
import mohammad.shahheydar.internshipprocessmanagement.mapper.OpinionMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.WeeklyReportRepository;
import mohammad.shahheydar.internshipprocessmanagement.service.Opinion.OpinionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class WeeklyReportService {

    private final WeeklyReportRepository weeklyReportRepository;
    private final OpinionMapper opinionMapper;

    public WeeklyReport save(WeeklyReport weeklyReport) {
        return weeklyReportRepository.save(weeklyReport);
    }

    public void update(WeeklyReport weeklyReport ,Long reportId) {
        WeeklyReport repoWeeklyReport = findById(reportId);
        repoWeeklyReport.setReportAttachment(weeklyReport.getReportAttachment());
        repoWeeklyReport.setReportTitle(weeklyReport.getReportTitle());
        repoWeeklyReport.setReportText(weeklyReport.getReportText());
        repoWeeklyReport.setWeakNumber(weeklyReport.getWeakNumber());
        repoWeeklyReport.setFromDate(weeklyReport.getFromDate());
        repoWeeklyReport.setToDate(weeklyReport.getToDate());
        repoWeeklyReport.setSupervisorConfirmation(null);
        repoWeeklyReport.setGuideTeacherConfirmation(null);
        save(repoWeeklyReport);
    }

    public WeeklyReport findById(Long id) {
       return weeklyReportRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "report not found"));
    }

    public WeeklyReport buildWeeklyReport(short weakNumber , String reportText , String reportTitle , String reportAttachmentPath) {
        WeeklyReport weeklyReport = new WeeklyReport();
        weeklyReport.setWeakNumber(weakNumber);
        weeklyReport.setReportText(reportText);
        weeklyReport.setReportTitle(reportTitle);
//        todo: file issue on front end system
        weeklyReport.setReportAttachment(reportAttachmentPath);
        return weeklyReport;
    }

    public void supervisorConfirm(Long id , Employee supervisor , OpinionDto confirm) {
        WeeklyReport weeklyReport = findById(id);
        Opinion opinion = opinionMapper.toEntity(confirm);
        opinion.setUser(supervisor);
        opinion.setOpinionTarget(weeklyReport);
        weeklyReport.setSupervisorConfirmation(opinion);
        save(weeklyReport);
    }

    public void guideTeacherConfirm(Long id , Employee guideTeacher , OpinionDto confirm) {
        WeeklyReport weeklyReport = findById(id);
        Opinion opinion = opinionMapper.toEntity(confirm);
        opinion.setUser(guideTeacher);
        opinion.setOpinionTarget(weeklyReport);
        weeklyReport.setGuideTeacherConfirmation(opinion);
        save(weeklyReport);
    }
}
