package org.example.passme.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Vault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "vault")
    private User user;
    @OneToMany(mappedBy = "vault")
    private Set<Password> passwords;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
