package mohammad.shahheydar.internshipprocessmanagement;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.entity.Role;
import mohammad.shahheydar.internshipprocessmanagement.entity.Student;
import mohammad.shahheydar.internshipprocessmanagement.model.RoleName;
import mohammad.shahheydar.internshipprocessmanagement.service.role.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class InternshipProcessManagementApplication {

//    private final RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(InternshipProcessManagementApplication.class, args);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOrigins("http://localhost:3000") // Allow React origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific HTTP methods
                        .allowCredentials(true) // Allow cookies or authentication headers
                        .allowedHeaders("*"); // Allow all headers
            }
        };
    }

//    @Bean
//    CommandLineRunner commandLineRunner() {
//        return args -> {
//            List<Role> roles = roleService.findAll();
//
//            if (roles.isEmpty()) {
//                Role admin = new Role();
//                admin.setName(RoleName.ADMIN);
//                roleService.save(admin);
//
//                Role EDUCATION_DEPARTMENT_MASTER = new Role();
//                EDUCATION_DEPARTMENT_MASTER.setName(RoleName.EDUCATION_DEPARTMENT_MASTER);
//                roleService.save(EDUCATION_DEPARTMENT_MASTER);
//
//                Role SUPERVISOR = new Role();
//                SUPERVISOR.setName(RoleName.SUPERVISOR);
//                roleService.save(SUPERVISOR);
//
//                Role GUIDE_TEACHER = new Role();
//                GUIDE_TEACHER.setName(RoleName.GUIDE_TEACHER);
//                roleService.save(GUIDE_TEACHER);
//
//                Role UNIVERSITY_TRAINING_STAFF = new Role();
//                UNIVERSITY_TRAINING_STAFF.setName(RoleName.UNIVERSITY_TRAINING_STAFF);
//                roleService.save(UNIVERSITY_TRAINING_STAFF);
//
//                Role DEPARTMENT_HEAD = new Role();
//                DEPARTMENT_HEAD.setName(RoleName.DEPARTMENT_HEAD);
//                roleService.save(DEPARTMENT_HEAD);
//
//                Role FACULTY_TRAINING_STAFF = new Role();
//                FACULTY_TRAINING_STAFF.setName(RoleName.FACULTY_TRAINING_STAFF);
//                roleService.save(FACULTY_TRAINING_STAFF);
//            }
//        };
//    }
}
