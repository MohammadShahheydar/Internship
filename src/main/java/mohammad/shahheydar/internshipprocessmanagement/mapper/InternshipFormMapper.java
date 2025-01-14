package mohammad.shahheydar.internshipprocessmanagement.mapper;

import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormDto;
import org.mapstruct.*;

// todo: bug: default value not set
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StudentMapper.class, EmployeeMapper.class, OpinionMapper.class})
public interface InternshipFormMapper extends BaseMapper<InternshipForm, InternshipFormDto> {

    @Mapping(target = "letterOfIntroductionPath", source = "letterOfIntroductionPath", qualifiedByName = "mapLetterOfIntroductionPath")
    @Override
    InternshipFormDto toDto(InternshipForm entity);

    @Named("mapLetterOfIntroductionPath")
    default String mapLetterOfIntroductionPath(String letterOfIntroductionPath) {
        if (letterOfIntroductionPath == null || letterOfIntroductionPath.isEmpty()) {
            return letterOfIntroductionPath;
        }
        return "http://localhost:8080/files/" + letterOfIntroductionPath;
    }
}
