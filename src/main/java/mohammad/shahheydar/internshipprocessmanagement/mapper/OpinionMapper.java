package mohammad.shahheydar.internshipprocessmanagement.mapper;

import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel =  MappingConstants.ComponentModel.SPRING , uses = {StudentMapper.class , EmployeeMapper.class , InternshipFormMapper.class})
public interface OpinionMapper extends BaseMapper<Opinion , OpinionDto>{
}
