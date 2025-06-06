package com.app.bloodbank.service;

import com.app.bloodbank.model.*;
import com.app.bloodbank.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;
    private final BloodBankRepository bloodBankRepository;
    private final BloodInventoryRepository bloodInventoryRepository;

    public DonationServiceImpl(DonationRepository donationRepository, DonorRepository donorRepository, BloodBankRepository bloodBankRepository, BloodInventoryRepository bloodInventoryRepository) {
        this.donationRepository = donationRepository;
        this.donorRepository = donorRepository;
        this.bloodBankRepository = bloodBankRepository;
        this.bloodInventoryRepository = bloodInventoryRepository;
    }

    @Transactional
    @Override
    public void registerDonation(LocalDate date, Long donorId, Long bloodBankId) {
        Donor donor = donorRepository.findById(donorId).orElseThrow(() -> new RuntimeException("Donor not found"));

        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId).orElseThrow(() -> new RuntimeException("BloodBank not found"));

        Donation donation = new Donation(date, donor, bloodBank, donor.getBloodType());
        donationRepository.save(donation);

        BloodType bloodType = donation.getBloodType();
        double quantity = 0.5; // fixed donation size

        Optional<BloodInventory> inventoryOpt = bloodInventoryRepository.findByBloodBankIdAndBloodTypeId(bloodBank.getId(), bloodType.getId());

        BloodInventory inventory = inventoryOpt.orElseGet(() -> {
            BloodInventory newInventory = new BloodInventory();
            newInventory.setBloodBank(bloodBank);
            newInventory.setBloodType(bloodType);
            newInventory.setQuantityInLiters(0.0);
            return newInventory;
        });

        inventory.setQuantityInLiters(inventory.getQuantityInLiters() + quantity);
        bloodInventoryRepository.save(inventory);

    }


    @Transactional
    @Override
    public void registerDonation(Long donorId, Long bloodBankId) {
        Donor donor = donorRepository.findById(donorId).orElseThrow(() -> new RuntimeException("Donor not found"));

        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId).orElseThrow(() -> new RuntimeException("BloodBank not found"));

        Donation donation = new Donation(LocalDate.now(), donor, bloodBank, donor.getBloodType());
        donationRepository.save(donation);

        BloodType bloodType = donation.getBloodType();
        double quantity = 0.5; // fixed donation size

        Optional<BloodInventory> inventoryOpt = bloodInventoryRepository.findByBloodBankIdAndBloodTypeId(bloodBank.getId(), bloodType.getId());

        BloodInventory inventory = inventoryOpt.orElseGet(() -> {
            BloodInventory newInventory = new BloodInventory();
            newInventory.setBloodBank(bloodBank);
            newInventory.setBloodType(bloodType);
            newInventory.setQuantityInLiters(0.0);
            return newInventory;
        });

        inventory.setQuantityInLiters(inventory.getQuantityInLiters() + quantity);
        bloodInventoryRepository.save(inventory);

    }

    @Transactional
    @Override
    public void updateDonation(Long id, Donation updatedData) {
        Donation existingDonation = getDonationById(id);

        existingDonation.setBloodType(updatedData.getBloodType());
        existingDonation.setDonor(updatedData.getDonor());
        existingDonation.setBloodBank(updatedData.getBloodBank());
        existingDonation.setDate(updatedData.getDate());

    }

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAllWithDonorAndBankAndInventory();
    }


    //TODO
    @Override
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElseThrow(() -> new RuntimeException("Donation not found"));
    }

    @Override
    public List<Donation> getDonationsByDonorId(Long donorId) {
        return donationRepository.findByDonorId(donorId);
    }
}
