package mohammad.shahheydar.internshipprocessmanagement.service.student;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Optional<Student> findById (long id) {
        return studentRepository.findById(id);
    }
}
