package mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.mapper.InternshipFormListMapper;
import mohammad.shahheydar.internshipprocessmanagement.mapper.InternshipMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormDto;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormListDto;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipProgressState;
import mohammad.shahheydar.internshipprocessmanagement.model.StudentDto;
import mohammad.shahheydar.internshipprocessmanagement.repository.InternshipFormRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InternshipFormService {

    private final InternshipFormRepository internshipFormRepository;
    private final InternshipFormListMapper internshipFormListMapper;
    private final InternshipMapper internshipMapper;

    public Page<InternshipFormListDto> findAll(Pageable pageable) {
        return internshipFormRepository.findAll(pageable).map(internshipFormListMapper::toDto);
    }

    public Page<InternshipFormListDto> findAllByProgressState(Pageable pageable, InternshipProgressState internshipProgressState) {
        return internshipFormRepository.findAllByProgressState(InternshipProgressState.UNIVERSITY_TRAINING_STAFF, pageable).map(internshipFormListMapper::toDto);
    }

    public Page<InternshipFormListDto> findAllByStudentId(long id, Pageable pageable) {
        return internshipFormRepository.findAllByStudentId(id, pageable).map(internshipFormListMapper::toDto);
    }

    public Optional<InternshipFormDto> findById(long id) {
        return internshipFormRepository.findById(id).map(internshipMapper::toDto);
    }

    public InternshipFormDto save(InternshipFormDto internshipFormDto, Long studentId) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentId);
        internshipFormDto.setStudent(studentDto);
        return internshipMapper.toDto(internshipFormRepository.save(internshipMapper.toEntity(internshipFormDto)));
    }
}
