package mohammad.shahheydar.internshipprocessmanagement.model;

import lombok.*;
import mohammad.shahheydar.internshipprocessmanagement.entity.Role;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto extends UserDto implements OpinionerDto {
    List<Role> roles;
}
