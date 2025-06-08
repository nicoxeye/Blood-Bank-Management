package com.app.bloodbank.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonManagedReference(value = "hospital-requests")
    private List<Request> requests;

    public Hospital() { }

    public Hospital(String name, Address address, String contactEmail, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
    }
}
