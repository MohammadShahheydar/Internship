package mohammad.shahheydar.internshipprocessmanagement.repository;

import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormProgressState;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternshipFormRepository extends JpaRepository<InternshipForm, Long> {

    //    jpql
    @Query(value = "select ir from InternshipForm ir where ir.student.id = :studentId")
    Page<InternshipForm> findAllByStudentId(long studentId, Pageable pageable);

    //    jpa
    Page<InternshipForm> findAllByStudent(Student student, Pageable pageable);

    Page<InternshipForm> findAllByProgressStateAndFormState(InternshipFormProgressState internshipFormProgressState, InternshipFormState formState, Pageable pageable);

    Optional<InternshipForm> findByStudentAndFormState(Student student, InternshipFormState formState);

    Integer countByStudentAndFormState(Student student, InternshipFormState formState);

    Optional<InternshipForm> findBySupervisorToken(String supervisorToken);
}
