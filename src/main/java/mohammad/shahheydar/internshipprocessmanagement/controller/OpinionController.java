package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.InternshipForm;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormProgressState;
import mohammad.shahheydar.internshipprocessmanagement.model.OpinionDto;
import mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm.InternshipFormService;
import mohammad.shahheydar.internshipprocessmanagement.service.Opinion.OpinionService;
import mohammad.shahheydar.internshipprocessmanagement.service.file.FileService;
import mohammad.shahheydar.internshipprocessmanagement.service.utils.UserExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class OpinionController {

    private final OpinionService opinionService;
    private final InternshipFormService internshipFormService;
    private final FileService fileService;

    @PostMapping("universityTrainingStaff/opinion/internship-forms/{id}")
    public ResponseEntity<String> universityTrainingStaffOpinionOnInternshipForms(@PathVariable Long id, @RequestBody @Valid OpinionDto opinionDto, HttpServletRequest request) {

        Employee employee = UserExtractor.getEmployee(request);

        InternshipForm internshipForm = internshipFormService.findOriginalById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "internship form not found"));

        opinionService.employeeOpinionOnInternshipForms(opinionDto, employee, internshipForm, InternshipFormProgressState.UNIVERSITY_TRAINING_STAFF);

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @PostMapping("departmentHead/opinion/internship-forms/{id}")
    public ResponseEntity<String> departmentHeadOpinionOnInternshipForms(
            @PathVariable Long id,
            @RequestParam(required = false) Long guideTeacherId,
            @RequestBody @Valid OpinionDto opinionDto,
            HttpServletRequest request
    ) {

        Employee employee = UserExtractor.getEmployee(request);

        InternshipForm internshipForm = internshipFormService.findOriginalById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "internship form not found"));

        opinionService.employeeOpinionOnInternshipForms(opinionDto, employee, internshipForm, InternshipFormProgressState.DEPARTMENT_HEAD, guideTeacherId);

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @PostMapping(value = "facultyTrainingStaff/opinion/internship-forms/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<String> facultyTrainingStaffOpinionOnInternshipForms(
            @PathVariable Long id,
            @RequestPart(value = "comment") @Valid @NotBlank String comment,
            @RequestPart(value = "confirm") @Valid @NotNull String confirm,
            @RequestPart(value = "file", required = false) MultipartFile file,
            HttpServletRequest request
    ) throws IOException {

        OpinionDto opinion = OpinionDto.builder().comment(comment).confirm(confirm.equals("1") || confirm.equals("true")).build();
        String letterOfIntroductionPath = fileService.saveFile(file, "letterOfIntroduction");

        Employee employee = UserExtractor.getEmployee(request);

        InternshipForm internshipForm = internshipFormService.findOriginalById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "internship form not found"));

        opinionService.employeeOpinionOnInternshipForms(opinion, employee, internshipForm, InternshipFormProgressState.FACULTY_TRAINING_STAFF, letterOfIntroductionPath);

        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }
}
