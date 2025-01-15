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

    public void saveWeeklyReport(Long internshipId, WeeklyReport weeklyReport) {
        Internship internship = internshipRepository.findById(internshipId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "internship not found"));
        WeeklyReport savedWeeklyReport = weeklyReportService.save(weeklyReport);
        internship.addWeeklyReport(savedWeeklyReport);
        internshipRepository.save(internship);
    }

    public void savePresenceAndAbsence(Long internshipId, PresenceAndAbsence presenceAndAbsence) {
        Internship internship = internshipRepository.findById(internshipId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "internship not found"));
        PresenceAndAbsence savedPresenceAndAbsence = presenceAndAbsenceService.save(presenceAndAbsence);
        internship.addPresenceAndAbsences(savedPresenceAndAbsence);
        internshipRepository.save(internship);
    }
}
