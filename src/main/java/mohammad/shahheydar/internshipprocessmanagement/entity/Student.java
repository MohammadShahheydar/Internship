package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import mohammad.shahheydar.internshipprocessmanagement.model.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {

//    todo: create model (optional)
//    private long facultyId;
//    private long collageId;
//    private long majorId;

    @ManyToOne(optional = true)
    @JoinColumn(name = "supervisor_id" )
    private Employee supervisor;

    private String field;

    private String degree;

    private String orientation;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
