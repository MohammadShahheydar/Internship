package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PresenceAndAbsenceDto extends BaseDto {
    @NotNull
    private short weakNumber;

    private OpinionDto supervisorConfirmation;

    private OpinionDto guideTeacherConfirmation;

    @NotBlank
    private String weekLog;

    @NotNull
    private short totalHour;

    private String fromDate;

    private String toDate;
}
