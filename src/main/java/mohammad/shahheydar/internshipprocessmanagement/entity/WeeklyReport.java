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

    @NotNull
    private byte weakNumber;

    private String reportAttachment;

    @NotBlank
    private String reportText;

    private Boolean supervisorConfirmation;

    @NotBlank
    private String reportTitle;
}
