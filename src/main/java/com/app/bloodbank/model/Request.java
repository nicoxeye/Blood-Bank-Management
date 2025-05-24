package com.app.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Request {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private BloodBank bloodBank;

    @ManyToOne
    private BloodType bloodType;

    private double quantityInLiters;

    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    private Status status; // PENDING, APPROVED, DENIED, COMPLETED

    public Request() {}

    public Request(Hospital hospital,BloodBank bloodBank, BloodType bloodType, double quantityInLiters, LocalDate requestDate) {
        this.hospital = hospital;
        this.bloodBank = bloodBank;
        this.bloodType = bloodType;
        this.quantityInLiters = quantityInLiters;
        this.requestDate = requestDate;
        this.status = Status.PENDING;
    }

}
