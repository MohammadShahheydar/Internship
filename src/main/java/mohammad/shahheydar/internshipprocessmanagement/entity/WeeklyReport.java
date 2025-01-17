package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class WeeklyReport extends BaseEntity {

    private short weakNumber;

    private String reportAttachment;

    private String reportText;

    private Boolean supervisorConfirmation;

    private Boolean guideTeacherConfirmation;

    private String reportTitle;

    private String fromDate;

    private String toDate;
}
