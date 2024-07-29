package team.onepoom.idk.domain;

import jakarta.persistence.MappedSuperclass;
import java.time.ZonedDateTime;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @CreationTimestamp
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
