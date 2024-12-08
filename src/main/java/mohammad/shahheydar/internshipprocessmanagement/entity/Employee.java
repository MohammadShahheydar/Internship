package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee extends User {

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "employee_roles" , joinColumns = @JoinColumn(name = "employee_id") , inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles;

    @Builder
    public Employee(String nationalId, String name, String email, String password, String phone, List<Role> roles) {
        super(nationalId, name, email, password, phone);
        this.roles = roles;
    }
}
