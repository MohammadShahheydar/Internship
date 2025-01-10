package mohammad.shahheydar.internshipprocessmanagement.repository;

import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.model.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//todo: how to create base repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    @Query(value = "select e from Employee e join fetch e.roles r where r.name = :roleName")
    List<Employee> findAllByRole(RoleName roleName);

//    todo: not work
    @Query(nativeQuery = true , value = "select e.* from internship.public.employee e left join internship.public.employee_roles er on er.employee_id = e.id left join internship.public.role r on r.id = er.role_id where r.name = :roleName and :name like CONCAT('%' , e.name, '%', e.lastname , '%')")
    List<Employee> findAllByRoleAndName(String roleName , String name);

    @Query(value = "select e from Employee e join fetch e.roles r where r.name = 'GUIDE_TEACHER' and e.id = :id")
    Optional<Employee> findGuideTeacherById(Long id);
}
