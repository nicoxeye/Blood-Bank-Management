package com.app.bloodbank.controller;

import com.app.bloodbank.model.BloodInventory;
import com.app.bloodbank.service.BloodInventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bloodinventory")
public class BloodInventoryController {

    private final BloodInventoryService bloodInventoryService;

    public BloodInventoryController(BloodInventoryService bloodInventoryService) {
        this.bloodInventoryService = bloodInventoryService;
    }

    @GetMapping()
    public List<BloodInventory> getBloodInventory() {
        return bloodInventoryService.getAllBloodInventory();
    }

    @GetMapping("/bloodbank/{bloodBankId}/bloodtype/{bloodTypeId}")
    public void getAvailableBloodStock(@PathVariable Long bloodBankId, @PathVariable Long bloodTypeId) {
        bloodInventoryService.getAvailableBloodStock(bloodBankId, bloodTypeId);
    }

}
