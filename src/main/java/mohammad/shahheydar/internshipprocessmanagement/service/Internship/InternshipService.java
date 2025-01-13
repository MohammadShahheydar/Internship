package mohammad.shahheydar.internshipprocessmanagement.service.Internship;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Internship;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.repository.InternshipRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;

    public Internship save(Internship internship) {
        return internshipRepository.save(internship);
    }

    public void update(Internship internship) {
        internshipRepository.save(internship);
    }

    public Optional<Internship> findByStudent(Student student) {
        return internshipRepository.findByStudent(student);
    }
}
