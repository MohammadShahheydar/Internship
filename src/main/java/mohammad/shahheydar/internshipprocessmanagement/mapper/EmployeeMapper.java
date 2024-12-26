package mohammad.shahheydar.internshipprocessmanagement.mapper;

import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.model.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING )
public interface EmployeeMapper extends BaseMapper<Employee , EmployeeDto> {
}
