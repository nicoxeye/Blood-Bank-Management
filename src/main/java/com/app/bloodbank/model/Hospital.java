package com.app.bloodbank.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    @OneToOne(mappedBy = "hospital")
    private User user;

}
