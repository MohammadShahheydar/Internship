package mohammad.shahheydar.internshipprocessmanagement.service.auth;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.mapper.StudentMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.LoginRequestDto;
import mohammad.shahheydar.internshipprocessmanagement.model.StudentDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentAuthService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final JwtService jwtService;

    public String authenticate(LoginRequestDto loginRequestDto) {
        Student student = studentRepository.findByEmail(loginRequestDto.username()).orElseThrow(() -> new NoSuchElementException("wrong username or password"));
        if (JwtService.verifyPassword(loginRequestDto.password(), student.getPassword()))
            return jwtService.generateToken(student);
        else
            throw new NoSuchElementException("wrong username or password");
    }

    public Student register(StudentDto studentDto) {
        studentDto.setPassword(JwtService.hashPassword(studentDto.getPassword()));
        return studentRepository.save(studentMapper.toEntity(studentDto));
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
