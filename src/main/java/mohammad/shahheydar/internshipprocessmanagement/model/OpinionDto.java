package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;
import mohammad.shahheydar.internshipprocessmanagement.entity.OpinionTarget;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinioner;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Opinion}
 */
public record OpinionDto(Date seen, @NotNull Boolean confirm, @NotNull @NotEmpty @NotBlank String comment,
                         Date commentDate, Opinioner user, OpinionTarget opinionTarget) implements Serializable {
}