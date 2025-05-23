package com.app.bloodbank.controller;
import com.app.bloodbank.model.Address;
import com.app.bloodbank.model.Donor;
import com.app.bloodbank.model.Patient;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final AddressRepository addressRepository;

    public PatientController(PatientService patientService, AddressRepository addressRepository) {
        this.patientService = patientService;
        this.addressRepository = addressRepository;
    }

    @GetMapping("")
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {

        Address address = patient.getAddress();
        List<Address> addresses = addressRepository.findAll();

        // creating new address if it doesn't exist :)
        if (!addresses.contains(address)) {
            addresses.add(address);
            addressRepository.saveAll(addresses);
        }

        patientService.addPatient(patient);
        return ResponseEntity.ok("Patient added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatientData) {

        Address address = updatedPatientData.getAddress();
        List<Address> addresses = addressRepository.findAll();

        // creating new address if it doesn't exist :)
        if (!addresses.contains(address)) {
            addresses.add(address);
            addressRepository.saveAll(addresses);
        }

        patientService.updatePatient(id, updatedPatientData);
        return ResponseEntity.ok("Patient updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

}
