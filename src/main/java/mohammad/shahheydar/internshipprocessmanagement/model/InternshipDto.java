package mohammad.shahheydar.internshipprocessmanagement.model;

import lombok.*;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;
import mohammad.shahheydar.internshipprocessmanagement.entity.PresenceAndAbsence;
import mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link mohammad.shahheydar.internshipprocessmanagement.entity.Internship}
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipDto extends BaseDto{
    String studentFinalReport;
    Timestamp studentObjectionDate;
    String studentObjectionText;
    InternshipState state;
    StudentDto student;
    EmployeeDto supervisor;
    String supervisorReport;
    EmployeeDto guideTeacher;
    InternshipFormDto internshipForm;
    List<WeeklyReportDto> weeklyReports;
    List<PresenceAndAbsenceDto> presenceAndAbsences;
    List<OpinionDto> opinions;
}