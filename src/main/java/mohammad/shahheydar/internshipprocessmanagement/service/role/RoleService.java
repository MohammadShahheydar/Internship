package mohammad.shahheydar.internshipprocessmanagement.service.role;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Role;
import mohammad.shahheydar.internshipprocessmanagement.model.RoleName;
import mohammad.shahheydar.internshipprocessmanagement.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findByName(RoleName roleName) {
        return roleRepository.getByName(roleName);
    }
}
