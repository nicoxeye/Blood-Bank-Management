package com.app.bloodbank.service;
import com.app.bloodbank.model.Patient;
import java.util.List;

public interface PatientService {
    public abstract void addPatient(Patient patient);
    public abstract void updatePatient(Long id, Patient updatedData);
    public abstract void deletePatient(Long id);
    public abstract List<Patient> getAllPatients();
}
