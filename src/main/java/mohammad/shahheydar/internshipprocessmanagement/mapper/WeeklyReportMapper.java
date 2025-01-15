package mohammad.shahheydar.internshipprocessmanagement.mapper;

import mohammad.shahheydar.internshipprocessmanagement.entity.WeeklyReport;
import mohammad.shahheydar.internshipprocessmanagement.model.WeeklyReportDto;
import org.mapstruct.*;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeeklyReportMapper extends BaseMapper<WeeklyReport , WeeklyReportDto> {

    @Mapping(target = "reportAttachment", source = "reportAttachment", qualifiedByName = "mapReportAttachment")
    @Override
    WeeklyReportDto toDto(WeeklyReport entity);

    @Named("mapReportAttachment")
    default String mapReportAttachment(String reportAttachment) {
        if (reportAttachment == null || reportAttachment.isEmpty()) {
            return reportAttachment;
        }
        return "http://localhost:8080/files/" + reportAttachment;
    }
}
