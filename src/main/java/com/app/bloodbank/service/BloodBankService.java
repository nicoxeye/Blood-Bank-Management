package com.app.bloodbank.service;

import com.app.bloodbank.model.BloodBank;

import java.util.List;

public interface BloodBankService {
    public abstract List<BloodBank> getAllBloodBanks();
    public abstract BloodBank getBloodBankById(Long id);
    public abstract void addBloodBank(BloodBank bloodBank);
    public abstract void updateBloodBank(Long id, BloodBank updatedData);
    public abstract void deleteBloodBank(BloodBank bloodBank);
    public abstract List<BloodBank> findBloodBankByCity(String city);
}
