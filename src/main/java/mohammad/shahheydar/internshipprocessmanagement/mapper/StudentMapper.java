package mohammad.shahheydar.internshipprocessmanagement.mapper;

import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.model.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING , uses = {EmployeeMapper.class})
public interface StudentMapper extends BaseMapper<Student, StudentDto> {
}
