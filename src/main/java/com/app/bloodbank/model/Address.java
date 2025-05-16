package com.app.bloodbank.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    private String country;
    private String city;
    private String street;
    private String zipcode;

    public Address() {}

    public Address(String country, String city, String street, String zipcode) {
        this.country = country.toUpperCase();
        this.city = city.toUpperCase();
        this.street = street.toUpperCase();
        this.zipcode = zipcode.trim().toUpperCase();
    }
}
