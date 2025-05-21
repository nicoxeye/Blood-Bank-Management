package com.app.bloodbank.service;

import com.app.bloodbank.model.Hospital;
import com.app.bloodbank.repository.HospitalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    @Override
    public void addHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Transactional
    @Override
    public void updateHospital(Long id, Hospital updatedData) {
        Hospital existingHospital = getHospitalById(id);

        existingHospital.setName(updatedData.getName());
        existingHospital.setAddress(updatedData.getAddress());
        existingHospital.setContactEmail(updatedData.getContactEmail());
        existingHospital.setPhoneNumber(updatedData.getPhoneNumber());
    }

    @Override
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hospital not found with ID: " + id));
    }
}
