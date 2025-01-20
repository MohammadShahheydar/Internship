package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
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
public class PresenceAndAbsence extends BaseEntity implements OpinionTarget{

    private short weakNumber;

    @OneToOne(fetch = FetchType.EAGER , cascade = {CascadeType.ALL} , orphanRemoval = true)
    private Opinion supervisorConfirmation;

    @OneToOne(fetch = FetchType.EAGER , cascade = {CascadeType.ALL} , orphanRemoval = true)
    private Opinion guideTeacherConfirmation;

    private String weekLog;

    private short totalHour;

    private String fromDate;

    private String toDate;
}
