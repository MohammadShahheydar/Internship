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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Opinion extends BaseEntity {
//    todo : how to make all columns nullable = false ?????
//    todo : how to make all relation optional = false ?????
    private Date seen;

    private Boolean confirm;

    private String comment;

    private Date commentDate;

//    @ManyToOne //todo
//    @JoinColumn(name = "internship_request_id")
//    private InternshipRequest internshipRequest;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
