package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(nullable = false)
    private byte WeakNumber;

    private String reportAttachment;

    private String reportText;

    private Boolean supervisorConfirmation;

    private String reportTitle;

    private Date reportDate;

//    @ManyToOne
//    @JoinColumn(name = "internship_id")
//    private Internship internship;
}
