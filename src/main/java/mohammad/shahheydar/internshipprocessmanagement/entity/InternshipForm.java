package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class InternshipForm extends BaseEntity{
    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(nullable = false , length = 13)
    private String phone;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false , length = 500)
    private String address;

    @Column(nullable = false)
    private String placeName;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false , length = 4)
    private String term;
}
