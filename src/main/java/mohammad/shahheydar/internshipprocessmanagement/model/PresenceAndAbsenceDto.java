package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;

public class PresenceAndAbsenceDto {
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
