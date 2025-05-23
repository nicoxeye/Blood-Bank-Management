package com.app.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference("donor-donation")
    private List<Donation> donations;

    public Donor() {}

    public Donor(String name, String surname, BloodType bloodType, LocalDate dateOfBirth, String gender, Address address, String phoneNumber) {
        this.name = name;
        this.surname = surname;
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
