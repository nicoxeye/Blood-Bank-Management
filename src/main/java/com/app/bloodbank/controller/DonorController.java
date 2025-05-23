package com.app.bloodbank.controller;

import com.app.bloodbank.model.Address;
import com.app.bloodbank.model.Donor;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService donorService;
    private final AddressRepository addressRepository;

    public DonorController(DonorService donorService, AddressRepository addressRepository) {
        this.donorService = donorService;
        this.addressRepository = addressRepository;
    }

    @GetMapping("")
    public List<Donor> getDonors() {
        return donorService.getAllDonors();
    }

    @PostMapping // works only if address exists;; need to fix this
    public ResponseEntity<String> addDonor(@RequestBody Donor donor) {

        Address address = donor.getAddress();
        List<Address> addresses = addressRepository.findAll();

        // creating new address if it doesn't exist :)
        if (!addresses.contains(address)) {
            addresses.add(address);
            addressRepository.saveAll(addresses);
        }

        donorService.addDonor(donor);
        return ResponseEntity.ok("Donor added successfully");
    }

    @PutMapping("/{id}") // works only if address exists;; need to fix this
    public ResponseEntity<String> updateDonor(@PathVariable Long id, @RequestBody Donor updatedDonorData) {

        Address address = updatedDonorData.getAddress();
        List<Address> addresses = addressRepository.findAll();
        if (!addresses.contains(address)) {
            addresses.add(address);
            addressRepository.saveAll(addresses);
        }

        donorService.updateDonor(id, updatedDonorData);
        return ResponseEntity.ok("Donor updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return ResponseEntity.ok("Donor deleted successfully");
    }

    @GetMapping("/{id}")
    public Donor getDonorById(@PathVariable Long id) {
        return donorService.getDonorById(id);
    }

}
