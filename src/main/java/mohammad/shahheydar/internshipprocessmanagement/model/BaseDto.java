package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    private long id;

    private LocalDate updatedAt;

    private LocalDate createdAt;

    private LocalDate deletedAt;
}
