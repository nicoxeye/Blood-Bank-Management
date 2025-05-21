package com.app.bloodbank.controller;

import com.app.bloodbank.model.BloodBank;
import com.app.bloodbank.service.BloodBankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloodbanks")
public class BloodBankController {

    private final BloodBankService bloodbankService;

    public BloodBankController(BloodBankService bloodbankService) {
        this.bloodbankService = bloodbankService;
    }

    @GetMapping("")
    public List<BloodBank> getAllBloodBanks() {
        return bloodbankService.getAllBloodBanks();
    }

    @GetMapping("/{id}")
    public BloodBank getBloodBankById(@PathVariable Long id) {
        return bloodbankService.getBloodBankById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBloodBank(@RequestBody BloodBank bloodbank) {
        bloodbankService.addBloodBank(bloodbank);
        return ResponseEntity.ok("Blood bank created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBloodBank(@RequestBody BloodBank updatedData, @PathVariable Long id) {
        bloodbankService.updateBloodBank(id, updatedData);
        return ResponseEntity.ok("Blood bank updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBloodBankById(@RequestParam Long id) {
        bloodbankService.deleteBloodBank(id);
        return ResponseEntity.ok("Blood bank deleted");
    }

    @GetMapping("/{city}")
    public List<BloodBank> findBloodBankByCity(@PathVariable String city) {
        return bloodbankService.findBloodBankByCity(city);
    }

}
