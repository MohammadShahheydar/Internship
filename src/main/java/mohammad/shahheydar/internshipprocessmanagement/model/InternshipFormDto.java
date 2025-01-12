package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@AllArgsConstructor
@Data
public class InternshipFormDto implements OpinionTargetDto {
    private StudentDto student;
    @NotNull
    private String phone;
    @NotNull
    private String state;
    @NotNull
    private String city;
    @NotNull
    private String supervisorName;
    @NotNull
    private String supervisorPhone;
    @URL
    private String supervisorWebUrl;
    @NotNull
    private String address;
    @NotNull
    private String placeName;
    @URL
    private String website;
    @NotNull
    private String term;
    @NotNull
    @Min(value = 80)
    private short passedUnit;

    private EmployeeDto guideTeacher;

    private String letterOfIntroductionPath;

    private String supervisorToken;

    private InternshipFormProgressState progressState;

    private InternshipFormState formState;

    private List<OpinionDto> opinions;
}
