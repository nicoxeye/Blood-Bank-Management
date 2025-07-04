package com.app.bloodbank.service;

import com.app.bloodbank.model.BloodBank;
import com.app.bloodbank.repository.BloodBankRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodBankServiceImpl implements BloodBankService {

    private final BloodBankRepository bloodbankRepository;

    public BloodBankServiceImpl(BloodBankRepository bloodbankRepository) {
        this.bloodbankRepository = bloodbankRepository;
    }

    @Override
    public List<BloodBank> getAllBloodBanks() {
        return bloodbankRepository.findAll();
    }

    @Override
    public BloodBank getBloodBankById(Long id) {
        return bloodbankRepository.findById(id).orElseThrow(() -> new RuntimeException("BloodBank not found"));
    }

    @Override
    public void addBloodBank(BloodBank bloodBank) {
        bloodbankRepository.save(bloodBank);
    }

    @Transactional
    @Override
    public void updateBloodBank(Long id, BloodBank updatedData) {
        BloodBank existingBloodBank = bloodbankRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("BloodBank not found"));

        existingBloodBank.setName(updatedData.getName());
        existingBloodBank.setAddress(updatedData.getAddress());
    }

    @Override
    public void deleteBloodBank(Long id) {
        bloodbankRepository.deleteById(id);
    }

    @Override
    public List<BloodBank> findBloodBankByCity(String city) {
        return bloodbankRepository.findByAddress_City(city);
    }



    public List<BloodBank> getAllBloodBanksWithDonationsCount() {
        List<BloodBank> bloodBanks = bloodbankRepository.findAll();

        bloodBanks.forEach(bloodBank -> {
            Long count = bloodbankRepository.countDonationsByBloodBankId(bloodBank.getId());
            bloodBank.setDonationsCount(count);
        });

        return bloodBanks;
    }
}
