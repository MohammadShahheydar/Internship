package mohammad.shahheydar.internshipprocessmanagement.repository;

import mohammad.shahheydar.internshipprocessmanagement.entity.Role;
import mohammad.shahheydar.internshipprocessmanagement.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role , Long> {
    Role getByName(RoleName roleName);
}
