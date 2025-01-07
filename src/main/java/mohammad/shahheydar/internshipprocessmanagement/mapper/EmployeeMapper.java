package mohammad.shahheydar.internshipprocessmanagement.mapper;

import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.model.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = MappingConstants.ComponentModel.SPRING , uses = {UserMapper.class})
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDto> {
}
