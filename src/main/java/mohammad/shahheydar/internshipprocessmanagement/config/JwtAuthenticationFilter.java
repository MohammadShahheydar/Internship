package mohammad.shahheydar.internshipprocessmanagement.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.service.user.EmployeeService;
import mohammad.shahheydar.internshipprocessmanagement.service.user.JwtService;
import mohammad.shahheydar.internshipprocessmanagement.service.user.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;

    private final JwtService jwtService;
    private final StudentService studentService;
    private final EmployeeService employeeService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/student/login") || path.equals("/student/register") || path.equals("/employee/login") || path.equals("/employee/register");
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        Optional<Cookie> employeeToken = Arrays.stream(request.getCookies() != null ? request.getCookies() : new Cookie[0])
                .filter(cookie -> "emp-token".equals(cookie.getName()))
                .findFirst();

        Optional<Cookie> studentToken = Arrays.stream(request.getCookies() != null ? request.getCookies() : new Cookie[0])
                .filter(cookie -> "stu-token".equals(cookie.getName()))
                .findFirst();

        if (studentToken.isEmpty() && employeeToken.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        try {
            if (employeeToken.isPresent()) {
                final String username = jwtService.extractUsername(employeeToken.get().getValue());
                Employee employee = employeeService.findByEmail(username).get();

                if (jwtService.isTokenValid(employeeToken.get().getValue(), employee))
                    request.setAttribute("employee", employee);
            }

            if (studentToken.isPresent()) {
                final String username = jwtService.extractUsername(studentToken.get().getValue());
                Student student = studentService.findByEmail(username).get();

                if (jwtService.isTokenValid(studentToken.get().getValue(), student))
                    request.setAttribute("student", student);
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}