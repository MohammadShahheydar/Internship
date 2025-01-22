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
    private Opinioner user;

    @AnyDiscriminator(value = DiscriminatorType.STRING)
    @AnyDiscriminatorValue(discriminator = "internshipForm", entity = InternshipForm.class)
    @AnyDiscriminatorValue(discriminator = "weeklyReport", entity = WeeklyReport.class)
    @AnyDiscriminatorValue(discriminator = "presenceAndAbsence", entity = PresenceAndAbsence.class)
    @AnyDiscriminatorValue(discriminator = "internship", entity = Internship.class)
    @AnyKeyJavaClass(Long.class)
    @Column(name = "opinion_target_type")
    @JoinColumn(name = "opinion_target_id")
    @Any(fetch = FetchType.LAZY)
    private OpinionTarget opinionTarget;
}
