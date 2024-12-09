package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Internship extends BaseEntity{

    private String studentFinalReport;

    private Timestamp studentObjectionDate;

    @Column(columnDefinition = "text")
    private String studentObjectionText;

    private int internshipState;

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE})
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;

    private String supervisorReport;

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE})
    @JoinColumn(name = "guide_teacher_id")
    private Employee guideTeacher;
}
