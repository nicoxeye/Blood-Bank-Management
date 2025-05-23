package com.app.bloodbank.controller;

import com.app.bloodbank.model.Address;
import com.app.bloodbank.model.Hospital;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> addHospital(Hospital hospital) {
        Address address = hospital.getAddress();
        List<Address> addresses = addressRepository.findAll();

        // creating new address if it doesn't exist :)
        if (!addresses.contains(address)) {
            addresses.add(address);
            addressRepository.saveAll(addresses);
        }

        hospitalService.addHospital(hospital);
        return ResponseEntity.ok("Hospital added");
    }

    @PutMapping
    public ResponseEntity<String> updateHospital(Long id, Hospital updatedHospital) {

        Address address = updatedHospital.getAddress();
        List<Address> addresses = addressRepository.findAll();
        if (!addresses.contains(address)) {
            addresses.add(address);
            addressRepository.saveAll(addresses);
        }

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

}
