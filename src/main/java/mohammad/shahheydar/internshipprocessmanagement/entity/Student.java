package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mohammad.shahheydar.internshipprocessmanagement.model.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User implements Opinioner {

//    todo: create model (optional)
//    private long facultyId;
//    private long collageId;
//    private long majorId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;

    private String field;

    private String degree;

    private String orientation;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
