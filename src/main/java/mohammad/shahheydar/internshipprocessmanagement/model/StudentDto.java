package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto extends UserDto implements OpinionerDto{
     EmployeeDto supervisor;
     @NotNull
     String field;
     @NotNull
     String degree;

     String orientation;
}
