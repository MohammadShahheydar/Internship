package mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipRequest;
import mohammad.shahheydar.internshipprocessmanagement.mapper.InternshipFormListMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormListDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.InternshipFormRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternshipFormService {

    private final InternshipFormRepository internshipFormRepository;
    private final InternshipFormListMapper internshipFormListMapper;

    public Page<InternshipFormListDto> findAll(Pageable pageable) {
        return internshipFormRepository.findAll(pageable).map(internshipFormListMapper::toDto);
    }
}
