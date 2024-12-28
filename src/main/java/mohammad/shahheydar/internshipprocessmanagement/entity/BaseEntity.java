package mohammad.shahheydar.internshipprocessmanagement.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;
}
