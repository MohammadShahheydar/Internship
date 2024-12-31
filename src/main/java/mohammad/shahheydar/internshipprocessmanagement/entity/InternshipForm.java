package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InternshipForm extends BaseEntity{
    @ManyToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH})
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH} , optional = true)
    @JoinColumn(name = "faculty_training_staff")
    private Employee facultyTrainingStaff;

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH} , optional = true)
    @JoinColumn(name = "department_head")
    private Employee departmentHead;

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH} , optional = true)
    @JoinColumn(name = "university_training_staff")
    private Employee universityTrainingStaff;

    @Column(nullable = false , length = 13)
    private String phone;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    private String supervisorName;

    private String supervisorPhone;

    @Column(nullable = true)
    private String supervisorWebUrl;

    @Column(nullable = false , length = 500)
    private String address;

    @Column(nullable = false)
    private String placeName;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false , length = 4)
    private String term;

    private short passedUnit;
}
