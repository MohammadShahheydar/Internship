package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipProgressState;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InternshipForm extends BaseEntity implements OpinionTarget {
    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH} , optional = true)
    @JoinColumn(name = "faculty_training_staff_id")
    private Employee facultyTrainingStaff;

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH} , optional = true)
    @JoinColumn(name = "department_head_id")
    private Employee departmentHead;

    @OneToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH} , optional = true)
    @JoinColumn(name = "university_training_staff_id")
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

    /**
     * 1:UNIVERSITY_TRAINING_STAFF
     * 2:DEPARTMENT_HEAD
     * 3:FACULTY_TRAINING_STAFF
     */
//    todo: db default value
    @Enumerated(EnumType.ORDINAL)
    private InternshipProgressState progressState = InternshipProgressState.FACULTY_TRAINING_STAFF;
//    todo: set expire logic for internship form

    @OneToMany(mappedBy = "opinionTarget")
    private List<Opinion> opinions;
}
