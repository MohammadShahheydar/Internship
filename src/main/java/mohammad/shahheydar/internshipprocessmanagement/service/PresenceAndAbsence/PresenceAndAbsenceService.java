package mohammad.shahheydar.internshipprocessmanagement.service.PresenceAndAbsence;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.PresenceAndAbsence;
import mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport;
import mohammad.shahheydar.internshipprocessmanagement.repository.PresenceAndAbsenceRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PresenceAndAbsenceService {

    private final PresenceAndAbsenceRepository presenceAndAbsenceRepository;

    public PresenceAndAbsence save(PresenceAndAbsence presenceAndAbsence) {
        return presenceAndAbsenceRepository.save(presenceAndAbsence);
    }

    public void update(PresenceAndAbsence presenceAndAbsence) {
        presenceAndAbsenceRepository.save(presenceAndAbsence);
    }
}
