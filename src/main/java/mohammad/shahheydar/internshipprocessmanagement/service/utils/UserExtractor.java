package mohammad.shahheydar.internshipprocessmanagement.service.utils;

import jakarta.servlet.http.HttpServletRequest;
import mohammad.shahheydar.internshipprocessmanagement.entity.Employee;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;

public class UserExtractor {
    public static Employee getEmployee(HttpServletRequest request) {
        return ((Employee) request.getAttribute("employee"));
    }

    public static Student getStudent(HttpServletRequest request) {
        return ((Student) request.getAttribute("student"));
    }
}
