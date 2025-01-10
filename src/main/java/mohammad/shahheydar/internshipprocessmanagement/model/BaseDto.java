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

    @JsonIgnore
    private LocalDate updatedAt;

    @JsonIgnore
    private LocalDate createdAt;

    @JsonIgnore
    private LocalDate deletedAt;
}
