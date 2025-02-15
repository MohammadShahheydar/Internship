package mohammad.shahheydar.internshipprocessmanagement.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.model.GrantedAuthority;
import mohammad.shahheydar.internshipprocessmanagement.service.utils.UserExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final List<String> allowedRole;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/files/letterOfIntroduction") ||  path.startsWith("/student") || path.equals("/student/login") || path.equals("/student/register") || path.equals("/employee/login") || path.equals("/employee/register");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Employee employee = UserExtractor.getEmployee(request);
        if (employee != null && employee.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(allowedRole::contains)) {
            filterChain.doFilter(request, response);
        }

        response.setStatus(HttpStatus.FORBIDDEN.value());
    }
}
