package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import mohammad.shahheydar.internshipprocessmanagement.model.RoleName;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//todo: builder not working on super
@Entity
public class Role extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    List<Employee> employees;
}
