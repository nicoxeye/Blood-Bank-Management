package com.app.bloodbank.service;

import com.app.bloodbank.model.BloodInventory;

import java.util.List;

public interface BloodInventoryService {
    public abstract List<BloodInventory> getAllBloodInventory();
    public abstract BloodInventory getBloodInventoryById(Long id);
    public abstract void addBloodInventory(BloodInventory bloodInventory);
    public abstract void updateBloodInventory(Long id, BloodInventory updatedData);
    public abstract void deleteBloodInventory(BloodInventory bloodInventory);

    public abstract void increaseBloodStock(Long bloodBankId, Long bloodTypeId, double liters);
    public abstract void decreaseBloodStock(Long bloodBankId, Long bloodTypeId, double liters);
    double getAvailableBloodStock(Long bloodBankId, Long bloodTypeId);
}
