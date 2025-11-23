package me.moamenhredeen.schnipsel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "operators")
public class Operator {

    @Id
    private Long id;

    @Column
    private String name;

    @OneToOne
    @MapsId
    private User user;

    @Embedded
    private AuditMetadata auditMetadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
