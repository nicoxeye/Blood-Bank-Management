package com.app.bloodbank.service;

import com.app.bloodbank.model.Donor;
import com.app.bloodbank.repository.DonorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorServiceImpl implements DonorService {
    private final DonorRepository donorRepository;

    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public void addDonor(Donor donor) {
        donorRepository.save(donor);
    }

    @Transactional
    @Override
    public void updateDonor(Long id, Donor updatedData) {
        Donor existingDonor = donorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Donor not found with ID: " + id));

        existingDonor.setName(updatedData.getName());
        existingDonor.setSurname(updatedData.getSurname());
        existingDonor.setDateOfBirth(updatedData.getDateOfBirth());
        existingDonor.setGender(updatedData.getGender());
        existingDonor.setPhoneNumber(updatedData.getPhoneNumber());
        existingDonor.setBloodType(updatedData.getBloodType());
        existingDonor.setAddress(updatedData.getAddress());

    }

    @Override
    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

    @Override
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @Override
    public Donor getDonorById(Long id) {
        return donorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Donor not found with ID: " + id));
    }
}
