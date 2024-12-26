package mohammad.shahheydar.internshipprocessmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public final class StudentDto extends UserDto {
     EmployeeDto supervisor;
     String field;
     String degree;
     String orientation;
}
