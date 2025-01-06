package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;
import mohammad.shahheydar.internshipprocessmanagement.entity.OpinionTarget;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinioner;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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