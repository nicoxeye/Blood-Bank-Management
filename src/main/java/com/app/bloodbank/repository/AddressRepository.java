package com.app.bloodbank.repository;

import com.app.bloodbank.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByCountryAndCityAndStreetAndZipcode(String country, String city, String street, String zipcode);
}
