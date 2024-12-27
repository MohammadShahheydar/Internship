package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.model.LoginRequestDto;
import mohammad.shahheydar.internshipprocessmanagement.model.StudentDto;
import mohammad.shahheydar.internshipprocessmanagement.service.auth.StudentAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentAuthController {

    private final StudentAuthService studentAuthService;

    @Value("${security.jwt.expiration-time}")
    private int jwtExpiration;

    @PostMapping("login")
    public ResponseEntity<String> login(@Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String token = studentAuthService.authenticate(loginRequestDto);

        Cookie cookie = new Cookie("emp-token", token);
        cookie.isHttpOnly();
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(jwtExpiration / 1000);

        response.addCookie(cookie);
        return ResponseEntity.ok("authenticate successfully");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid StudentDto studentDto) {
        studentAuthService.register(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("employee created");
    }
}
