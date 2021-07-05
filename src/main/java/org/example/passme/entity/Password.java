package org.example.passme.entity;

import javax.persistence.*;

@Entity
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "vault_id", nullable = false)
    private Vault vault;
}
