package mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.mapper.InternshipFormListMapper;
import mohammad.shahheydar.internshipprocessmanagement.mapper.InternshipFormMapper;
import mohammad.shahheydar.internshipprocessmanagement.model.*;
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
    private final InternshipFormMapper internshipFormMapper;

    public Page<InternshipFormListDto> findAll(Pageable pageable) {
        return internshipFormRepository.findAll(pageable).map(internshipFormListMapper::toDto);
    }

    public Page<InternshipFormListDto> findAllByProgressState(Pageable pageable, InternshipFormProgressState internshipFormProgressState) {
        return internshipFormRepository.findAllByProgressStateAndFormState(internshipFormProgressState, InternshipFormState.IN_PROGRESS, pageable).map(internshipFormListMapper::toDto);
    }

    public Page<InternshipFormListDto> findAllByStudentId(long id, Pageable pageable) {
        return internshipFormRepository.findAllByStudentId(id, pageable).map(internshipFormListMapper::toDto);
    }

    public Optional<InternshipFormDto> findById(long id) {
        return internshipFormRepository.findById(id).map(internshipFormMapper::toDto);
    }

    public Optional<InternshipForm> findOriginalById(long id) {
        return internshipFormRepository.findById(id);
    }

    public InternshipFormDto save(InternshipFormDto internshipFormDto, Long studentId) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentId);
        internshipFormDto.setStudent(studentDto);
        InternshipForm entity = internshipFormMapper.toEntity(internshipFormDto);
        entity.setFormState(InternshipFormState.IN_PROGRESS);
        return internshipFormMapper.toDto(internshipFormRepository.save(entity));
    }

    public void updateInternshipFormProgressStateAndEmployeeState(InternshipForm internshipForm, Employee employee, InternshipFormProgressState internshipFormProgressState, InternshipFormState formState) {
        switch (internshipFormProgressState) {
            case UNIVERSITY_TRAINING_STAFF:
                internshipForm.setUniversityTrainingStaff(employee);
                break;
            case DEPARTMENT_HEAD:
                internshipForm.setDepartmentHead(employee);
                break;
            case FACULTY_TRAINING_STAFF:
                internshipForm.setFacultyTrainingStaff(employee);
                break;
        }
        internshipForm.setProgressState(internshipFormProgressState);
        internshipForm.setFormState(formState);
        internshipFormRepository.save(internshipForm);
    }

    public Optional<InternshipForm> findStudentInProgressForm(Long studentId) {
        Student stu = new Student();
        stu.setId(studentId);
        return internshipFormRepository.findByStudentAndFormState(stu, InternshipFormState.IN_PROGRESS);
    }

    public Integer studentInProgressFormCount(Long studentId , InternshipFormState internshipFormState) {
        Student stu = new Student();
        stu.setId(studentId);
        return internshipFormRepository.countByStudentAndFormState(stu , internshipFormState);
    }
}
