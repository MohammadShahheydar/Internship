package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormDto;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormListDto;
import mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm.InternshipFormService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class InternshipFormController {

    private final InternshipFormService internshipFormService;

    @GetMapping("all")
    public ResponseEntity<Page<InternshipFormListDto>> getAll() {
        return ResponseEntity.ok(internshipFormService.findAll(Pageable.ofSize(20)));
    }

    @PostMapping("student/internship-forms/save")
    public ResponseEntity<InternshipFormDto> save(@Valid @RequestBody InternshipFormDto internshipFormDto , HttpServletRequest request) {
        return ResponseEntity.ok(internshipFormService.save(internshipFormDto , request));
    }
}
