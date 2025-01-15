package mohammad.shahheydar.internshipprocessmanagement.service.weeklyReport;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport;
import mohammad.shahheydar.internshipprocessmanagement.repository.WeeklyReportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeeklyReportService {

    private final WeeklyReportRepository weeklyReportRepository;

    public WeeklyReport save(WeeklyReport weeklyReport) {
        return weeklyReportRepository.save(weeklyReport);
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

    public void confirm(Long id) {
        WeeklyReport weeklyReport = findById(id);
        weeklyReport.setSupervisorConfirmation(true);
        save(weeklyReport);
    }
}
