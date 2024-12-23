package mohammad.shahheydar.internshipprocessmanagement.repository;

import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipFormRepository extends JpaRepository<InternshipForm , Long> {

    @Query(value = "select ir from InternshipForm ir where ir.student.id = :studentId")
    public Page<InternshipForm> findByStudent(long studentId , Pageable pageable);
}
