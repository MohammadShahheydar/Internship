package mohammad.shahheydar.internshipprocessmanagement.service.PresenceAndAbsence;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.PresenceAndAbsence;
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

    public PresenceAndAbsence findById(Long id) {
        return presenceAndAbsenceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "report not found"));
    }

    public void supervisorConfirm(Long id) {
        PresenceAndAbsence presenceAndAbsence = findById(id);
        presenceAndAbsence.setSupervisorConfirmation(true);
        save(presenceAndAbsence);
    }

    public void guideTeacherConfirm(Long id) {
        PresenceAndAbsence presenceAndAbsence = findById(id);
        presenceAndAbsence.setGuideTeacherConfirmation(true);
        save(presenceAndAbsence);
    }
}
