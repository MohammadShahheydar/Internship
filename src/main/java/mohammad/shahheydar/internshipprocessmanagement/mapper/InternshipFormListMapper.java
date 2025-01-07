package mohammad.shahheydar.internshipprocessmanagement.mapper;

import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormListDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InternshipFormListMapper extends BaseMapper<InternshipForm, InternshipFormListDto> {
}
