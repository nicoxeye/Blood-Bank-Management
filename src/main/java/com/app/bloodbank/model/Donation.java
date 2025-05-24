package com.app.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Donation {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "bloodbank_id")
    private BloodBank bloodBank;

    @ManyToOne
    @JoinColumn(name = "bloodtype_id")
    private BloodType bloodType;

    public Donation() { }

    public Donation(LocalDate date, Donor donor, BloodBank bloodBank, BloodType bloodType) {
        this.date = date;
        this.donor = donor;
        this.bloodBank = bloodBank;
        this.bloodType = bloodType;
    }
}
