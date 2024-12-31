package mohammad.shahheydar.internshipprocessmanagement.controller;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.model.InternshipFormListDto;
import mohammad.shahheydar.internshipprocessmanagement.service.InternshipForm.InternshipFormService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("internship-forms")
@CrossOrigin(origins = "http://localhost:3000")
public class InternshipFormController {

    private final InternshipFormService internshipFormService;

    @GetMapping("all")
    public ResponseEntity<Page<InternshipFormListDto>> getAll() {
        return ResponseEntity.ok(internshipFormService.findAll(Pageable.ofSize(20)));
    }
}
