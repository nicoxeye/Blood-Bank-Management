package com.app.bloodbank.controller;
import com.app.bloodbank.model.Patient;
import com.app.bloodbank.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping("")
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping // works only if address exists;; need to fix this
    //TODO: CHECK THIS!! ON: POSTMAN
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
        return ResponseEntity.ok("Patient added successfully");
    }

    @PutMapping("/{id}") // works only if address exists;; need to fix this
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatientData) {
        patientService.updatePatient(id, updatedPatientData);
        return ResponseEntity.ok("Patient updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }
}
