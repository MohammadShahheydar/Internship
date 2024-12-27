package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import mohammad.shahheydar.internshipprocessmanagement.model.RoleName;
import mohammad.shahheydar.internshipprocessmanagement.model.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//todo: read about wild generic
@Entity
public class Role extends BaseEntity implements GrantedAuthority {
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Override
    public String getAuthority() {
        return name.name();
    }

//    @ManyToMany(mappedBy = "roles")
//    List<Employee> employees;
}
