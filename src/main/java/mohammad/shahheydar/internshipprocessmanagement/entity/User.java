package mohammad.shahheydar.internshipprocessmanagement.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mohammad.shahheydar.internshipprocessmanagement.model.UserDetail;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
//todo index
public abstract class User extends BaseEntity implements UserDetail {
    @Column(nullable = false, length = 10)
    private String nationalId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetail.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetail.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetail.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetail.super.isEnabled();
    }

}
