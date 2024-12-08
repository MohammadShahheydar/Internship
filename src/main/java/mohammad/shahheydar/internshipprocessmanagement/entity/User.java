package mohammad.shahheydar.internshipprocessmanagement.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class User extends BaseEntity{
    @Column(nullable = false, length = 10)
    private String nationalId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    public User(String nationalId, String name, String email, String password, String phone) {
        this.nationalId = nationalId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
