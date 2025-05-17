package com.app.bloodbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    @ManyToOne
    private Donor donor;

    @ManyToOne
    private BloodBank bloodBank;

    @ManyToOne
    private BloodType bloodType;

    public Donation() { }

    public Donation(LocalDate date, Donor donor, BloodBank bloodBank, BloodType bloodType) {
        this.date = date;
        this.donor = donor;
        this.bloodBank = bloodBank;
        this.bloodType = bloodType;
    }
}
