package mohammad.shahheydar.internshipprocessmanagement.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@Data
public class InternshipFormDto {
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
    private short passedUnit;
}
