package mohammad.shahheydar.internshipprocessmanagement.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.model.EmployeeDto;
import mohammad.shahheydar.internshipprocessmanagement.model.LoginRequestDto;
import mohammad.shahheydar.internshipprocessmanagement.service.auth.EmployeeAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeAuthController {

    private final EmployeeAuthService employeeAuthService;

    @Value("${security.jwt.expiration-time}")
    private int jwtExpiration;

    @PostMapping("login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String token = employeeAuthService.authenticate(loginRequestDto);

        Cookie cookie = new Cookie("emp-token", token);
        cookie.isHttpOnly();
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(jwtExpiration / 1000);

        response.addCookie(cookie);
        return ResponseEntity.ok("authenticate successfully");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody EmployeeDto employeeDto) {
        employeeAuthService.register(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("employee created");
    }

    @GetMapping("get")
    public ResponseEntity<EmployeeDto> getStudent(HttpServletRequest request) {
        return ResponseEntity.ok(employeeAuthService.getAuthenticatedEmployee(request));
    }
}
