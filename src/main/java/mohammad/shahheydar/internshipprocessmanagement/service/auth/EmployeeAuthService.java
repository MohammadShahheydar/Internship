package mohammad.shahheydar.internshipprocessmanagement.service.auth;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.config.RoleCache;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.Role;
import mohammad.shahheydar.internshipprocessmanagement.mapper.EmployeeMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.EmployeeDto;
import mohammad.shahheydar.internshipprocessmanagement.model.LoginRequestDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeAuthService {
    private final EmployeeRepository employeeRepository;
    private final RoleCache roleCache;
    private final EmployeeMapper employeeMapper;
    private final JwtService jwtService;

    public String authenticate(LoginRequestDto loginRequestDto) {
        Employee employee = employeeRepository.findByEmail(loginRequestDto.username()).orElseThrow(() -> new NoSuchElementException("wrong username or password"));
        System.out.println(employee.getPassword());
        System.out.println(JwtService.hashPassword(loginRequestDto.password()));
        if (JwtService.verifyPassword(loginRequestDto.password() ,employee.getPassword()))
            return jwtService.generateToken(employee);
        else
            throw new NoSuchElementException("wrong username or password");
    }

    public Employee register(EmployeeDto employeeDto) {
        List<Role> roles = employeeDto.getRoles().stream().map(role -> roleCache.getRoleByName(role.getName().name())).toList();
        employeeDto.setRoles(roles);
        employeeDto.setPassword(JwtService.hashPassword(employeeDto.getPassword()));
        return employeeRepository.save(employeeMapper.toEntity(employeeDto));
    }

    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
