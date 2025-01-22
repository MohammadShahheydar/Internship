package mohammad.shahheydar.internshipprocessmanagement.repository;

import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.Internship;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InternshipRepository extends JpaRepository<Internship , Long> {

    Optional<Internship> findByStudent(Student student);

    List<Internship> findBySupervisor(Employee supervisor);

    List<Internship> findByGuideTeacherAndState(Employee teacher , InternshipState state);
}
