package mohammad.shahheydar.internshipprocessmanagement.mapper;

import mohammad.shahheydar.internshipprocessmanagement.entity.Internship;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StudentMapper.class, EmployeeMapper.class , WeeklyReportMapper.class , PresenceAndAbsenceMapper.class , InternshipFormMapper.class , OpinionMapper.class})
public interface InternshipMapper extends BaseMapper<Internship , InternshipDto> {
}
