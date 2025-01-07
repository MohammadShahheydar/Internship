package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto extends UserDto implements OpinionerDto {
    EmployeeDto supervisor;
    @NotNull
    String field;
    @NotNull
    String degree;

    String orientation;
}
