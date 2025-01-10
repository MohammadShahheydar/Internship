package mohammad.shahheydar.internshipprocessmanagement.service.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.config.RoleCache;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.Role;
import mohammad.shahheydar.internshipprocessmanagement.mapper.EmployeeMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.EmployeeDto;
import mohammad.shahheydar.internshipprocessmanagement.model.LoginRequestDto;
import mohammad.shahheydar.internshipprocessmanagement.model.RoleName;
import mohammad.shahheydar.internshipprocessmanagement.repository.EmployeeRepository;
import mohammad.shahheydar.internshipprocessmanagement.service.utils.UserExtractor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleCache roleCache;
    private final EmployeeMapper employeeMapper;
    private final JwtService jwtService;

    public String authenticate(LoginRequestDto loginRequestDto) {
        Employee employee = employeeRepository.findByEmail(loginRequestDto.username()).orElseThrow(() -> new NoSuchElementException("wrong username or password"));
        if (JwtService.verifyPassword(loginRequestDto.password(), employee.getPassword()))
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

    public EmployeeDto getAuthenticatedEmployee(HttpServletRequest request) {
        Employee employee = UserExtractor.getEmployee(request);
        EmployeeDto employeeDto = employeeMapper.toDto(employee);
        employeeDto.setPassword(null);
        return employeeDto;
    }

    public List<EmployeeDto> getAllGuideTeachers(String name) {
        if (name == null)
            return employeeRepository.findAllByRole(RoleName.GUIDE_TEACHER).stream().map(employeeMapper::toDto).toList();
        else
            return employeeRepository.findAllByRoleAndName(RoleName.GUIDE_TEACHER.name(), name).stream().map(employeeMapper::toDto).toList();
    }

    public Optional<Employee> findGuideTeacherById(Long id) {
        return employeeRepository.findGuideTeacherById(id);
    }
}
