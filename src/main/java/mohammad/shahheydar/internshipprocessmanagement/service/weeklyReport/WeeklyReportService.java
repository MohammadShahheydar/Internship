package mohammad.shahheydar.internshipprocessmanagement.service.weeklyReport;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport;
import mohammad.shahheydar.internshipprocessmanagement.repository.WeeklyReportRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeeklyReportService {

    private final WeeklyReportRepository weeklyReportRepository;

    public WeeklyReport save(WeeklyReport weeklyReport) {
        return weeklyReportRepository.save(weeklyReport);
    }

    public void update(WeeklyReport weeklyReport) {
        weeklyReportRepository.save(weeklyReport);
    }

}
