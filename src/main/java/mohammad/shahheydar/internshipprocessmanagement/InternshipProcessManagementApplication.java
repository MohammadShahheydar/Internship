package mohammad.shahheydar.internshipprocessmanagement;

import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternshipProcessManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternshipProcessManagementApplication.class, args);
        Student build = Student.builder().build();
        System.out.println(build.getName());
    }

}
