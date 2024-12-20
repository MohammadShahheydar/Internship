package mohammad.shahheydar.internshipprocessmanagement.repository;

import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
//todo: how to create base repository
public interface StudentRepository extends JpaRepository<Student , Long> {
    public Student findByEmail(String email);
}
