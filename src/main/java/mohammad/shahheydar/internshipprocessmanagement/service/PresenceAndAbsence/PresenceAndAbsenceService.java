package mohammad.shahheydar.internshipprocessmanagement.service.PresenceAndAbsence;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.PresenceAndAbsence;
import mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport;
import mohammad.shahheydar.internshipprocessmanagement.repository.PresenceAndAbsenceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PresenceAndAbsenceService {

    private final PresenceAndAbsenceRepository presenceAndAbsenceRepository;

    public PresenceAndAbsence save(PresenceAndAbsence presenceAndAbsence) {
        return presenceAndAbsenceRepository.save(presenceAndAbsence);
    }

    public void update(PresenceAndAbsence presenceAndAbsence , Long reportId) {
        PresenceAndAbsence repoPresenceAndAbsence = findById(reportId);
        repoPresenceAndAbsence.setTotalHour(presenceAndAbsence.getTotalHour());
        repoPresenceAndAbsence.setWeakNumber(presenceAndAbsence.getWeakNumber());
        repoPresenceAndAbsence.setWeekLog(presenceAndAbsence.getWeekLog());
        repoPresenceAndAbsence.setSupervisorConfirmation(null);
        repoPresenceAndAbsence.setGuideTeacherConfirmation(null);
        save(repoPresenceAndAbsence);
    }

    public PresenceAndAbsence findById(Long id) {
        return presenceAndAbsenceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "report not found"));
    }

    public void supervisorConfirm(Long id , Boolean confirm) {
        PresenceAndAbsence presenceAndAbsence = findById(id);
        presenceAndAbsence.setSupervisorConfirmation(confirm);
        save(presenceAndAbsence);
    }

    public void guideTeacherConfirm(Long id , Boolean confirm) {
        PresenceAndAbsence presenceAndAbsence = findById(id);
        presenceAndAbsence.setGuideTeacherConfirmation(confirm);
        save(presenceAndAbsence);
    }
}
