package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {

//    todo: create model (optional)
    private long facultyId;
    private long collageId;
    private long majorId;
}
