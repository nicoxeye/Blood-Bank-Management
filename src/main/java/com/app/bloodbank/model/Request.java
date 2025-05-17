package com.app.bloodbank.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private BloodType bloodType;

    private double quantityInLiters;

    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    private Status status; // PENDING, APPROVED, DENIED, COMPLETED

    public enum Status {
        PENDING,
        APPROVED,
        DENIED,
        COMPLETED
    }

}
