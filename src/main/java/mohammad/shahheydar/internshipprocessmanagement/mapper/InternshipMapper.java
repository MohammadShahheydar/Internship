package mohammad.shahheydar.internshipprocessmanagement.mapper;

import mohammad.shahheydar.internshipprocessmanagement.entity.Internship;
import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipDto;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormDto;
import org.mapstruct.*;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StudentMapper.class, EmployeeMapper.class , WeeklyReportMapper.class , PresenceAndAbsenceMapper.class , InternshipFormMapper.class , OpinionMapper.class})
public interface InternshipMapper extends BaseMapper<Internship , InternshipDto> {
    @Mapping(target = "studentFinalReport", source = "studentFinalReport", qualifiedByName = "mapStudentFinalReport")
    @Override
    InternshipDto toDto(Internship entity);

    @Named("mapStudentFinalReport")
    default String mapStudentFinalReport(String studentFinalReport) {
        if (studentFinalReport == null || studentFinalReport.isEmpty()) {
            return studentFinalReport;
        }
        return "http://localhost:8080/files/" + studentFinalReport;
    }
}
