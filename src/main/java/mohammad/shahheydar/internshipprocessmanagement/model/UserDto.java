package mohammad.shahheydar.internshipprocessmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto{

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
