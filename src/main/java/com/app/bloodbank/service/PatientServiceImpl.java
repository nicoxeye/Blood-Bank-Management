package com.app.bloodbank.service;

import com.app.bloodbank.model.Patient;
import com.app.bloodbank.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    @Transactional
    // don't know if this is the correct way to do it; have to check it;
    public void updatePatient(Long id, Patient updatedData) {
        Patient existingPatient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + id));

        existingPatient.setName(updatedData.getName());
        existingPatient.setSurname(updatedData.getSurname());
        existingPatient.setDateOfBirth(updatedData.getDateOfBirth());
        existingPatient.setGender(updatedData.getGender());
        existingPatient.setPhoneNumber(updatedData.getPhoneNumber());
        existingPatient.setAddress(updatedData.getAddress());
        existingPatient.setBloodType(updatedData.getBloodType());
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + id));
    }

}
