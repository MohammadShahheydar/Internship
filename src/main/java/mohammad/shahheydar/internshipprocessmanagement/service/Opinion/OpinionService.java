package mohammad.shahheydar.internshipprocessmanagement.service.Opinion;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.OpinionTarget;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinioner;
import mohammad.shahheydar.internshipprocessmanagement.mapper.OpinionMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipProgressState;
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
    public OpinionDto universityTrainingStaffOpinionOnInternshipForms(OpinionDto opinionDto , Opinioner opinioner , OpinionTarget opinionTarget , InternshipProgressState internshipProgressState) {
        Employee employee = (Employee) opinioner;
        internshipFormService.updateInternshipFormProgressStateAndEmployeeState(opinionTarget.getId() , internshipProgressState , employee);
        opinionDto.setUser(opinioner);
        opinionDto.setOpinionTarget(opinionTarget);
        return this.save(opinionDto);
    }

    public OpinionDto save (OpinionDto opinionDto) {
        return opinionMapper.toDto(opinionRepository.save(opinionMapper.toEntity(opinionDto)));
    }
}
