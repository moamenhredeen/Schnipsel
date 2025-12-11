package me.moamenhredeen.schnipsel.model;

import jakarta.persistence.*;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Optional;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SoftDelete(
        strategy = SoftDeleteType.TIMESTAMP,
        columnName = "deleted_at"
)
public class AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", updatable = false)
    @NotFound(action = NotFoundAction.EXCEPTION)
    @CreatedBy
    private Operator createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_modified_by", insertable = false)
    @NotFound(action = NotFoundAction.EXCEPTION)
    @LastModifiedBy
    private Operator lastModifiedBy;

    @Column(name = "created_date", updatable = false)
    @CreatedDate()
    private Instant createdDate;

    @Column(name = "last_modified_date", insertable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

    public Optional<Operator> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public void setCreatedBy(Operator createdBy) {
        this.createdBy = createdBy;
    }

    public Optional<Operator> getLastModifiedBy() {
        return Optional.ofNullable(lastModifiedBy);
    }

    public void setLastModifiedBy(Operator lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Optional<Instant> getLastModifiedDate() {
        return Optional.ofNullable(lastModifiedDate);
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
