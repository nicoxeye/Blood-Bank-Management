package com.app.bloodbank.controller;

import com.app.bloodbank.model.BloodBank;
import com.app.bloodbank.model.Donation;
import com.app.bloodbank.model.Donor;
import com.app.bloodbank.service.DonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("")
    public List<Donation> getDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    // register donation by donor ID and blood bank ID
    @PostMapping("/register")
    public ResponseEntity<String> registerDonation(@RequestParam Long donorId, @RequestParam Long bloodBankId) {
        donationService.registerDonation(donorId, bloodBankId);
        return ResponseEntity.ok("Donation registered for donor ID " + donorId + " and blood bank ID " + bloodBankId);
    }

    // register donation with date
    @PostMapping("/register-with-date")
    public ResponseEntity<String> registerDonationWithDate(@RequestParam String date, @RequestParam Long donorId, @RequestParam Long bloodBankId) {
        LocalDate localDate = LocalDate.parse(date);
        donationService.registerDonation(localDate, donorId, bloodBankId);
        return ResponseEntity.ok("Donation registered for " + localDate + " by donor ID " + donorId + " and blood bank ID " + bloodBankId);
    }

    @PutMapping("/{id}")
    public void updateDonation(@PathVariable Long id, @RequestBody Donor updatedData) {
        donationService.updateDonation(id, updatedData);
    }

    @DeleteMapping("/{id}")
    public void deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
    }
}

