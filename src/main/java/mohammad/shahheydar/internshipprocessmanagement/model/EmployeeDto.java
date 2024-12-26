package mohammad.shahheydar.internshipprocessmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import mohammad.shahheydar.internshipprocessmanagement.entity.Role;

import java.util.List;

@AllArgsConstructor
public class EmployeeDto extends UserDto {
    List<Role> roles;
}
