package mohammad.shahheydar.internshipprocessmanagement.repository;

import mohammad.shahheydar.internshipprocessmanagement.entity.Internship;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InternshipRepository extends JpaRepository<Internship , Long> {

    Optional<Internship> findByStudent(Student student);
}
