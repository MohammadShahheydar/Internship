package mohammad.shahheydar.internshipprocessmanagement.model;

import lombok.*;
import mohammad.shahheydar.internshipprocessmanagement.entity.Role;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeDto extends UserDto {
    List<Role> roles;
}
