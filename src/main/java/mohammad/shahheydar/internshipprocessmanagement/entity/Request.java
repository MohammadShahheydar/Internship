package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Request extends BaseEntity {
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
}
