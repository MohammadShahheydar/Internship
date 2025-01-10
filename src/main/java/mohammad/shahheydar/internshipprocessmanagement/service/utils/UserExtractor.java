package mohammad.shahheydar.internshipprocessmanagement.service.utils;

import jakarta.servlet.http.HttpServletRequest;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserExtractor {
    public static Employee getEmployee(HttpServletRequest request) {
        Object employee = request.getAttribute("employee");
        if (employee == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "employee not logged in");
        return ((Employee) employee);
    }

    public static Student getStudent(HttpServletRequest request) {
        Object user = request.getAttribute("student");
        if (user == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "student not logged in");
        return ((Student) user);
    }
}
