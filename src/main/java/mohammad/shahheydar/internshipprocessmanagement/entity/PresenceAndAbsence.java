package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PresenceAndAbsence extends BaseEntity {

    @NotNull
    private short weakNumber;

    private Boolean supervisorConfirmation;

    private Boolean guideTeacherConfirmation;

    @NotBlank
    private String weekLog;

    @NotNull
    private short totalHour;
}
