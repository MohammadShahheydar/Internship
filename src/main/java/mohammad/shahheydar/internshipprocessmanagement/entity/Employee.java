package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mohammad.shahheydar.internshipprocessmanagement.model.EmployeeDto;
import mohammad.shahheydar.internshipprocessmanagement.model.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee extends User implements Opinioner {

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles;
//
//    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
//    private List<Opinion> opinions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}
