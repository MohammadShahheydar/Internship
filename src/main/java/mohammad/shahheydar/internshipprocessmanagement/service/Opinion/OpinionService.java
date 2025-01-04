package mohammad.shahheydar.internshipprocessmanagement.service.Opinion;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinion;
import mohammad.shahheydar.internshipprocessmanagement.entity.OpinionTarget;
import mohammad.shahheydar.internshipprocessmanagement.entity.Opinioner;
import mohammad.shahheydar.internshipprocessmanagement.mapper.OpinionMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.OpinionRepository;
import mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm.InternshipFormService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final OpinionMapper opinionMapper;
    private final InternshipFormService internshipFormService;

    public OpinionDto save (OpinionDto opinionDto , Opinioner opinioner , OpinionTarget opinionTarget) {
        return opinionMapper.toDto(opinionRepository.save(opinionMapper.toEntity(opinionDto)));
    }
}
