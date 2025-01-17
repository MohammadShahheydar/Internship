package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport}
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeeklyReportDto extends BaseDto {

    @NotNull
    short weakNumber;
    String reportAttachment;
    @NotBlank
    String reportText;
    Boolean supervisorConfirmation;
    @NotBlank
    String reportTitle;

    String from;

    String to;
}