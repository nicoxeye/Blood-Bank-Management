package com.app.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BloodInventory {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonBackReference(value = "bloodbank-inventories")
    private BloodBank bloodBank;

    @ManyToOne
    @JoinColumn(name = "blood_type_id")
    private BloodType bloodType;

    private double quantityInLiters;

    public BloodInventory() {}

    public BloodInventory(BloodBank bloodBank, BloodType bloodType, double quantityInLiters) {
        this.bloodBank = bloodBank;
        this.bloodType = bloodType;
        this.quantityInLiters = quantityInLiters;
    }

}
