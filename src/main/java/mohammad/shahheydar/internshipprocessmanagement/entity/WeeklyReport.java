package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WeeklyReport extends BaseEntity implements OpinionTarget{

    private short weakNumber;

    private String reportAttachment;

    private String reportText;

    @OneToOne(fetch = FetchType.EAGER , cascade = {CascadeType.ALL} , orphanRemoval = true)
    private Opinion supervisorConfirmation;

    @OneToOne(fetch = FetchType.EAGER , cascade = {CascadeType.ALL} , orphanRemoval = true)
    private Opinion guideTeacherConfirmation;

    private String reportTitle;

    private String fromDate;

    private String toDate;
}
