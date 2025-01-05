package mohammad.shahheydar.internshipprocessmanagement.service.Opinion;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.OpinionTarget;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinioner;
import mohammad.shahheydar.internshipprocessmanagement.mapper.OpinionMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormProgressState;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormState;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.OpinionRepository;
import mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm.InternshipFormService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final OpinionMapper opinionMapper;
    private final InternshipFormService internshipFormService;

    @Transactional
    public void employeeOpinionOnInternshipForms(OpinionDto opinionDto , Opinioner opinioner , OpinionTarget opinionTarget , InternshipFormProgressState internshipFormProgressState) {
//        todo: check FACULTY_TRAINING_STAFF is the last one in process
        InternshipFormState formState = opinionDto.getConfirm() ? internshipFormProgressState == InternshipFormProgressState.FACULTY_TRAINING_STAFF ? InternshipFormState.CONFIRM : InternshipFormState.IN_PROGRESS : InternshipFormState.FAIL;
        Employee employee = (Employee) opinioner;
        internshipFormService.updateInternshipFormProgressStateAndEmployeeState(opinionTarget.getId() , employee , internshipFormProgressState, formState);
        opinionDto.setUser(opinioner);
        opinionDto.setOpinionTarget(opinionTarget);
        this.save(opinionDto);
    }

    public OpinionDto save (OpinionDto opinionDto) {
        return opinionMapper.toDto(opinionRepository.save(opinionMapper.toEntity(opinionDto)));
    }
}
