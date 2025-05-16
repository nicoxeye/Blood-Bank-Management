package com.app.bloodbank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Patient {
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

    public Patient() {}

    public Patient(String name, String surname, LocalDate dateOfBirth, String gender, Address address, String phoneNumber) {
        this.name = name.toUpperCase();
        this.surname = surname.toUpperCase();
        this.dateOfBirth = dateOfBirth;

        switch (gender) {
            case "MALE", "M" -> this.gender = Gender.MALE;
            case "FEMALE", "F" -> this.gender = Gender.FEMALE;
            case "OTHER", "O" -> this.gender = Gender.OTHER;
        }

        this.address = address;

        if (phoneNumber.length() == 9 || phoneNumber.length() == 12) {
            this.phoneNumber = phoneNumber;
        }
    }

    @Override
    public String toString() {
        return name+", "+surname+", "+dateOfBirth.getYear()+", "+gender+", "+address.getCountry()+", "+address.getCity()+", "+phoneNumber;
    }
}
