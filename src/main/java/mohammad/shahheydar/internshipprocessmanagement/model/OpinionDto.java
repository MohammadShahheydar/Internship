package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;

import java.util.Date;

/**
 * DTO for {@link Opinion}
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public final class OpinionDto {
    private Date seen;
    private @NotBlank Boolean confirm;
    private @NotBlank String comment;
    private Date commentDate;
    private OpinionerDto user;
    private OpinionTargetDto opinionTarget;
}