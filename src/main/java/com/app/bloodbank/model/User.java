package com.app.bloodbank.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    public enum Role {
        ADMIN,
        HOSPITAL,
        DONOR
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private Hospital hospital; // requesting blood

    @OneToOne
    private Donor donor; // donation history

    public User() {}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
