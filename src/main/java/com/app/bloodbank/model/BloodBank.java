package com.app.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class BloodBank {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "bloodBank")
    @JsonBackReference("bloodbank-donation")
    private List<Donation> donations = new ArrayList<>();

    @OneToMany(mappedBy = "bloodBank")
    private List<BloodInventory> bloodInventories = new ArrayList<>();

    public BloodBank() { }

    public BloodBank(String name, Address address) {
        this.name = name;
        this.address = address;
    }

}
