package mohammad.shahheydar.internshipprocessmanagement.mapper;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.model.EmployeeDto;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.model.StudentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OpinionMapper {

    private final StudentMapper studentMapper;
    private final EmployeeMapper employeeMapper;

    public OpinionDto toDto(Opinion opinion) {
        if (opinion == null) {
            return null;
        }

        OpinionDto.OpinionDtoBuilder opinionDto = OpinionDto.builder();

        opinionDto.confirm(opinion.getConfirm());
        opinionDto.comment(opinion.getComment());
        opinionDto.commentDate(opinion.getCommentDate());
        if (opinion.getUser() instanceof Student)
            opinionDto.user(studentMapper.toDto((Student) opinion.getUser()));
        if (opinion.getUser() instanceof Employee)
            opinionDto.user(employeeMapper.toDto((Employee) opinion.getUser()));

        return opinionDto.build();
    }

    public Opinion toEntity(OpinionDto dto) {
        if (dto == null) {
            return null;
        }

        Opinion opinion = new Opinion();

        opinion.setConfirm(dto.getConfirm());
        opinion.setComment(dto.getComment());
        opinion.setCommentDate(dto.getCommentDate());
        if (dto.getUser() instanceof StudentDto)
            opinion.setUser(studentMapper.toEntity((StudentDto) opinion.getUser()));
        if (dto.getUser() instanceof EmployeeDto)
            opinion.setUser(employeeMapper.toEntity((EmployeeDto) opinion.getUser()));

        return opinion;
    }

    public List<Opinion> toEntityList(List<OpinionDto> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Opinion> list = new ArrayList<Opinion>(dtoList.size());
        for (OpinionDto opinionDto : dtoList) {
            list.add(toEntity(opinionDto));
        }

        return list;
    }

    public List<OpinionDto> toDtoList(List<Opinion> entityList) {
        if (entityList == null) {
            return null;
        }

        List<OpinionDto> list = new ArrayList<OpinionDto>(entityList.size());
        for (Opinion opinion : entityList) {
            list.add(toDto(opinion));
        }

        return list;
    }
}
