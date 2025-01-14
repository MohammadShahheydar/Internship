package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    private byte weakNumber;

    private boolean supervisorConfirmation;

    private String weekLog;

    private short totalHour;
}
