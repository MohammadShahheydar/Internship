package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student extends User {

//    todo: create model (optional)
    private long facultyId;
    private long collageId;
    private long majorId;

    @Builder
    public Student(String nationalId, String name, String email, String password, String phone, long facultyId, long collageId, long majorId) {
        super(nationalId, name, email, password, phone);
        this.facultyId = facultyId;
        this.collageId = collageId;
        this.majorId = majorId;
    }
}
