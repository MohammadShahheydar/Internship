package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.model.LoginRequestDto;
import mohammad.shahheydar.internshipprocessmanagement.model.StudentDto;
import mohammad.shahheydar.internshipprocessmanagement.service.auth.StudentAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9000")
public class StudentAuthController {

    private final StudentAuthService studentAuthService;

    @Value("${security.jwt.expiration-time}")
    private int jwtExpiration;

    @PostMapping("login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String token = studentAuthService.authenticate(loginRequestDto);

        Cookie cookie = new Cookie("stu-token", token);
        cookie.isHttpOnly();
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(jwtExpiration / 1000);

        response.addCookie(cookie);
        return ResponseEntity.ok("authenticate successfully");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody StudentDto studentDto) {
        studentAuthService.register(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("student created");
    }


    @GetMapping("get")
    public ResponseEntity<StudentDto> getStudent(HttpServletRequest request) {
        return ResponseEntity.ok(studentAuthService.getAuthenticatedStudent(request));
    }
}
