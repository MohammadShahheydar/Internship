package mohammad.shahheydar.internshipprocessmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 10 , max = 10)
    String nationalId;

    @NotNull
    String name;

    @NotNull
    String lastname;

    @NotNull
    String email;

    @NotNull
    String phoneNumber;

    @NotNull
    String password;
}
