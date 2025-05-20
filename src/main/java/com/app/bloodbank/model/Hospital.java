package com.app.bloodbank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Hospital {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Address address;

    private String contactEmail;

    private String phoneNumber;

    @OneToMany(mappedBy = "hospital")
    private List<Request> requests;

    public Hospital() { }

    public Hospital(String name, Address address, String contactEmail, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
    }
}
