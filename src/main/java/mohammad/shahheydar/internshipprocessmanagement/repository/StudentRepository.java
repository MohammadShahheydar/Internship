package mohammad.shahheydar.internshipprocessmanagement.repository;

import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//todo: how to create base repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
