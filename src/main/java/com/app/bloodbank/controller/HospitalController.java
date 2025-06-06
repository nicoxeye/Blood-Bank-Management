package com.app.bloodbank.controller;

import com.app.bloodbank.model.Address;
import com.app.bloodbank.model.BloodBank;
import com.app.bloodbank.model.Hospital;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    private final HospitalService hospitalService;
    private final AddressRepository addressRepository;

    public HospitalController(HospitalService hospitalService, AddressRepository addressRepository) {
        this.hospitalService = hospitalService;
        this.addressRepository = addressRepository;
    }

    @GetMapping()
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @PostMapping("")
    public ResponseEntity<String> addHospital(@RequestBody Hospital hospital) {

        Address incomingAddress = hospital.getAddress();

        // finding an address with the same metadata
        Optional<Address> existingAddress = addressRepository.findByCountryAndCityAndStreetAndZipcode(
                incomingAddress.getCountry(),
                incomingAddress.getCity(),
                incomingAddress.getStreet(),
                incomingAddress.getZipcode());

        Address finalAddress = existingAddress.orElseGet(() -> addressRepository.save(incomingAddress));

        hospital.setAddress(finalAddress);

        hospitalService.addHospital(hospital);
        return ResponseEntity.ok("Hospital added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateHospital(@PathVariable Long id, @RequestBody Hospital updatedHospital) {

        Address incomingAddress = updatedHospital.getAddress();

        // finding an address with the same metadata
        Optional<Address> existingAddress = addressRepository.findByCountryAndCityAndStreetAndZipcode(
                incomingAddress.getCountry(),
                incomingAddress.getCity(),
                incomingAddress.getStreet(),
                incomingAddress.getZipcode());

        Address finalAddress = existingAddress.orElseGet(() -> addressRepository.save(incomingAddress));

        updatedHospital.setAddress(finalAddress);

        hospitalService.updateHospital(id, updatedHospital);
        return ResponseEntity.ok("Hospital updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.ok("Hospital deleted");
    }

    @GetMapping("/{id}")
    public Hospital getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id);
    }


    @GetMapping("/city/{city}")
    public List<Hospital> findHospitalByCity(@PathVariable String city) {
        city = city.toUpperCase();
        return hospitalService.findHospitalByCity(city);
    }
}
