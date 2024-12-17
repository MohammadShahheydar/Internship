package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InternshipRequest extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String text;

    private int internshipConfirmation;

    private String agreementUploadUrl;

    @ManyToOne
    @JoinColumn(name = "faculty_training_staff_id")
    private Employee facultyTrainingStaff;

    @ManyToOne
    @JoinColumn(name = "university_training_staff_id")
    private Employee universityTrainingStaff;

    @ManyToOne
    @JoinColumn(name = "state Department_head_id")
    private Employee stateDepartmentHead;

    @ManyToOne
    @JoinColumn(name = "department_head_id")
    private Employee departmentHeadId;

    @OneToOne
    @JoinColumn(name = "university_training_staff_opinion_id")
    private Opinion universityTrainingStaffOpinion;

    @OneToOne
    @JoinColumn(name = "guide_teature_opinion_id")
    private Opinion guideTeatureOpinion;

    @OneToOne
    @JoinColumn(name = "department_head_opinion_id")
    private Opinion departmentHeadOpinionId;

    private String state;
}
