package io.pilpin.mre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "CustomRevisionEntity")
@Table(name = "_audit")
@RevisionEntity(CustomRevisionEntityListener.class)
public class CustomRevisionEntity implements Comparable<CustomRevisionEntity>, Serializable {
    @Id
    @GeneratedValue
    @RevisionNumber
    private Long id;

    @RevisionTimestamp
    private LocalDateTime timestamp;

    private UUID userId;

    @Override
    public int compareTo(CustomRevisionEntity o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CustomRevisionEntity)) return false;

        return Objects.equals(this.id, ((CustomRevisionEntity) obj).id);
    }


}
