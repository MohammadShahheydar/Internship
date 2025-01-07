package mohammad.shahheydar.internshipprocessmanagement.config;

import lombok.RequiredArgsConstructor;
import mohammad.shahheydar.internshipprocessmanagement.model.RoleName;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> authentication() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtAuthenticationFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> supervisorAuthorize() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(List.of(RoleName.SUPERVISOR.name(), RoleName.ADMIN.name())));
        registrationBean.addUrlPatterns("/supervisor/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> guideTeacherAuthorize() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(List.of(RoleName.GUIDE_TEACHER.name(), RoleName.ADMIN.name())));
        registrationBean.addUrlPatterns("/guideTeacher/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> educationDepartmentMasterAuthorize() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(List.of(RoleName.EDUCATION_DEPARTMENT_MASTER.name(), RoleName.ADMIN.name())));
        registrationBean.addUrlPatterns("/educationDepartmentMaster/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> universityTrainingStaffAuthorize() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(List.of(RoleName.UNIVERSITY_TRAINING_STAFF.name(), RoleName.ADMIN.name())));
        registrationBean.addUrlPatterns("/universityTrainingStaff/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> departmentHeadAuthorize() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(List.of(RoleName.DEPARTMENT_HEAD.name(), RoleName.ADMIN.name())));
        registrationBean.addUrlPatterns("/departmentHead/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> facultyTrainingStaffAuthorize() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(List.of(RoleName.FACULTY_TRAINING_STAFF.name(), RoleName.ADMIN.name())));
        registrationBean.addUrlPatterns("/facultyTrainingStaff/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> adminAuthorize() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(List.of(RoleName.ADMIN.name())));
        registrationBean.addUrlPatterns("/admin/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }


//    todo: it has circular dependency injection error
//    todo: it define in main class
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
//                        .allowedHeaders("*");
//            }
//        };
//    }
}
