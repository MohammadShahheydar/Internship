package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InternshipRequest extends BaseEntity {
//    todo: rename
    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String text;

    private int internshipConfirmation;

    private String agreementUploadUrl;

    @OneToOne
    @JoinColumn(name = "faculty_training_staff_id")
    private Employee facultyTrainingStaff;

    @OneToOne
    @JoinColumn(name = "university_training_staff_id")
    private Employee universityTrainingStaff;

    @OneToOne
    @JoinColumn(name = "State Department_head_id")
    private Employee stateDepartmentHead;

    private String state;
}
