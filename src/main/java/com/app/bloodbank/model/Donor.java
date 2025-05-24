package com.app.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    public Donor() {}

    public Donor(String name, String surname, BloodType bloodType, LocalDate dateOfBirth, String gender, Address address, String phoneNumber) {
        this.name = name.toUpperCase();
        this.surname = surname.toUpperCase();
        this.bloodType = bloodType;
        this.dateOfBirth = dateOfBirth;
        switch (gender) {
            case "MALE", "M" -> this.gender = Gender.MALE;
            case "FEMALE", "F" -> this.gender = Gender.FEMALE;
            case "OTHER", "O" -> this.gender = Gender.OTHER;
        }
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}
