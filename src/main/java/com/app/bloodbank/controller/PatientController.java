package com.app.bloodbank.controller;
import com.app.bloodbank.model.Address;
import com.app.bloodbank.model.Donor;
import com.app.bloodbank.model.Patient;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

        Address incomingAddress = patient.getAddress();

        // finding an address with the same metadata
        Optional<Address> existingAddress = addressRepository.findByCountryAndCityAndStreetAndZipcode(
                incomingAddress.getCountry(),
                incomingAddress.getCity(),
                incomingAddress.getStreet(),
                incomingAddress.getZipcode());

        Address finalAddress = existingAddress.orElseGet(() -> addressRepository.save(incomingAddress));

        patient.setAddress(finalAddress);

        patientService.addPatient(patient);
        return ResponseEntity.ok("Patient added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatientData) {

        Address incomingAddress = updatedPatientData.getAddress();

        // finding an address with the same metadata
        Optional<Address> existingAddress = addressRepository.findByCountryAndCityAndStreetAndZipcode(
                incomingAddress.getCountry(),
                incomingAddress.getCity(),
                incomingAddress.getStreet(),
                incomingAddress.getZipcode());

        Address finalAddress = existingAddress.orElseGet(() -> addressRepository.save(incomingAddress));

        updatedPatientData.setAddress(finalAddress);

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
