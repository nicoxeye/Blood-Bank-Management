package com.app.bloodbank.controller;

import com.app.bloodbank.model.Donor;
import com.app.bloodbank.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {
    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GetMapping("")
    public List<Donor> getDonors() {
        return donorService.getAllDonors();
    }

    @PostMapping // works only if address exists;; need to fix this
    //TODO: CHECK THIS!! ON: POSTMAN
    public ResponseEntity<String> addDonor(@RequestBody Donor donor) {
        donorService.addDonor(donor);
        return ResponseEntity.ok("Donor added successfully");
    }

    @PutMapping("/{id}") // works only if address exists;; need to fix this
    public ResponseEntity<String> updateDonor(@PathVariable Long id, @RequestBody Donor updatedDonorData) {
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
