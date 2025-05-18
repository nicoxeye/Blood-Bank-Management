package com.app.bloodbank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Donor {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "blood_type_id")
    private BloodType bloodType;

    private LocalDate dateOfBirth;

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String phoneNumber;

    @OneToMany(mappedBy = "donor")
    private List<Donation> donations;

    public Donor() {}

    public Donor(String name, String surname, BloodType bloodType, LocalDate dateOfBirth, Gender gender, Address address, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.bloodType = bloodType;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}
