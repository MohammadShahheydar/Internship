package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Opinion extends BaseEntity {
    private Date seen;

    private Boolean confirm;

    private String comment;

    private Date commentDate;

    @AnyDiscriminator(value = DiscriminatorType.STRING)
    @AnyDiscriminatorValue(discriminator = "student", entity = Student.class)
    @AnyDiscriminatorValue(discriminator = "employee", entity = Employee.class)
    @AnyKeyJavaClass(Long.class)
    @Column(name = "user_type")
    @JoinColumn(name = "user_id")
    @Any
//    todo: change to 4 columns for each employee
    private Opinioner user;

    @AnyDiscriminator(value = DiscriminatorType.STRING)
    @AnyDiscriminatorValue(discriminator = "internshipForm", entity = InternshipForm.class)
    @AnyKeyJavaClass(Long.class)
    @Column(name = "opinion_target_type")
    @JoinColumn(name = "opinion_target_id")
    @Any(fetch = FetchType.LAZY)
    //    todo: change to 2 columns for (internship form , weekly report , ....)
    private OpinionTarget opinionTarget;
}
