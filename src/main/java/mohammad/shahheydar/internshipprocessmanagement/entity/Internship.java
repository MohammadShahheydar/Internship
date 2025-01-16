package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipState;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Internship extends BaseEntity {

    @OneToMany()
    @JoinColumn(name = "internship_id")
    List<WeeklyReport> weeklyReports;

    @OneToMany()
    @JoinColumn(name = "internship_id")
    List<PresenceAndAbsence> presenceAndAbsences;

    private String studentFinalReport;

    private Timestamp studentObjectionDate;

    @Column(columnDefinition = "text")
    private String studentObjectionText;

    @Column(nullable = false)
    @Enumerated
    private InternshipState state;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;

    private String supervisorReport;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "guide_teacher_id")
    private Employee guideTeacher;

    public void addWeeklyReport(WeeklyReport weeklyReport) {
        this.weeklyReports.add(weeklyReport);
    }

    public void addPresenceAndAbsences(PresenceAndAbsence presenceAndAbsences) {
        this.presenceAndAbsences.add(presenceAndAbsences);
    }
}
