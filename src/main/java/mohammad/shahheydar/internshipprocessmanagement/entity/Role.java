package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mohammad.shahheydar.internshipprocessmanagement.model.GrantedAuthority;
import mohammad.shahheydar.internshipprocessmanagement.model.RoleName;

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
