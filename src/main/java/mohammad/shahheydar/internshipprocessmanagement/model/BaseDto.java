package mohammad.shahheydar.internshipprocessmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private long id;

    private LocalDate updatedAt;

    private LocalDate createdAt;

    @JsonIgnore
    private LocalDate deletedAt;
}
