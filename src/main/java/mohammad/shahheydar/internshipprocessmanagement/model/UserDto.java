package mohammad.shahheydar.internshipprocessmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    String nationalId;
    String name;
    String lastname;
    String email;
    String phoneNumber;
}
