package org.example.passme.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vaults")
public class Vault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne(mappedBy = "vault")
    private User user;

    @OneToMany(mappedBy = "vault", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Login> logins;

    public Vault() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Login> getLogins() {
        return logins;
    }

    public void setLogins(Set<Login> logins) {
        this.logins = logins;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
