package mohammad.shahheydar.internshipprocessmanagement.entity;

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
public class PresenceAndAbsence extends BaseEntity {

    private boolean supervisorConfirmation;

    private Date start;//todo why here?

    private Date end;//todo why here?

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;
}
