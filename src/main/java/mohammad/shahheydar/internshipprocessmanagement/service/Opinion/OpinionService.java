package mohammad.shahheydar.internshipprocessmanagement.service.Opinion;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;
import mohammad.shahheydar.internshipprocessmanagement.mapper.OpinionMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormProgressState;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormState;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.OpinionRepository;
import mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm.InternshipFormService;
import mohammad.shahheydar.internshipprocessmanagement.service.user.EmployeeService;
import mohammad.shahheydar.internshipprocessmanagement.service.user.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final OpinionMapper opinionMapper;
    private final InternshipFormService internshipFormService;
    private final EmployeeService employeeService;

    //    todo: 2 @Transactional . ok ? is it work ?
    @Transactional(rollbackFor = Throwable.class)
    public void employeeOpinionOnInternshipForms(
            OpinionDto opinionDto,
            Employee opinioner,
            InternshipForm opinionTarget,
            InternshipFormProgressState internshipFormProgressState,
            Long guideTeacherId
    ) {
        Employee employee = guideTeacherId != null ? employeeService.findGuideTeacherById(guideTeacherId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "guide teacher not found ")) : null;
        opinionTarget.setGuideTeacher(employee);
        employeeOpinionOnInternshipForms(opinionDto, opinioner, opinionTarget, internshipFormProgressState);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void employeeOpinionOnInternshipForms(
            OpinionDto opinionDto,
            Employee opinioner,
            InternshipForm opinionTarget,
            InternshipFormProgressState internshipFormProgressState,
            String letterOfIntroductionPath
    ) {
        opinionTarget.setLetterOfIntroductionPath(letterOfIntroductionPath);
        opinionTarget.setSupervisorToken(JwtService.hashPassword(System.currentTimeMillis() + opinionTarget.getSupervisorName() + opinionTarget.getSupervisorPhone()));
        opinionTarget.setIsSupervisorTokenValid(true);
        employeeOpinionOnInternshipForms(opinionDto, opinioner, opinionTarget, internshipFormProgressState);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void employeeOpinionOnInternshipForms(
            OpinionDto opinionDto,
            Employee opinioner,
            InternshipForm opinionTarget,
            InternshipFormProgressState internshipFormProgressState
    ) {
        InternshipFormState formState = opinionDto.getConfirm() ? internshipFormProgressState == InternshipFormProgressState.FACULTY_TRAINING_STAFF ? InternshipFormState.CONFIRM : InternshipFormState.IN_PROGRESS : InternshipFormState.FAIL;
        internshipFormService.updateInternshipFormProgressStateAndEmployeeState(opinionTarget, opinioner, internshipFormProgressState, formState);
        Opinion opinion = opinionMapper.toEntity(opinionDto);
        opinion.setUser(opinioner);
        opinion.setOpinionTarget(opinionTarget);
        this.save(opinion);
    }

    public OpinionDto save(OpinionDto opinionDto) {
        opinionDto.setCommentDate(new Date());
        return opinionMapper.toDto(opinionRepository.save(opinionMapper.toEntity(opinionDto)));
    }

    public OpinionDto save(Opinion opinion) {
        opinion.setCommentDate(new Date());
        return opinionMapper.toDto(opinionRepository.save(opinion));
    }
}
