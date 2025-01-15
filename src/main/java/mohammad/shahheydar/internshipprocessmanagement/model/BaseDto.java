package mohammad.shahheydar.internshipprocessmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private long id;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime deletedAt;
}
