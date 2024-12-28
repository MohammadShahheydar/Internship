package mohammad.shahheydar.internshipprocessmanagement.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Role;
import mohammad.shahheydar.internshipprocessmanagement.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RoleCache {
    private final Map<String, Role> rolesMap = new HashMap<>();

    private final RoleRepository roleRepository;

    @PostConstruct
    public void loadRolesIntoCache() {
        List<Role> roles = roleRepository.findAll();
        roles.forEach(role -> rolesMap.put(role.getName().name(), role));
    }

    public Role getRoleByName(String name) {
        return rolesMap.get(name.toUpperCase());
    }
}
