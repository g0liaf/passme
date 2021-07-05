package org.example.passme.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Vault implements Serializable {
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

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "vault", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Login> logins;

    public Vault() {
    }

    public Vault(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Login> getPasswords() {
        return logins;
    }

    public void setPasswords(Set<Login> logins) {
        this.logins = logins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vault vault = (Vault) o;
        return user.equals(vault.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
