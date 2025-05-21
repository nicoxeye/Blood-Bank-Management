package com.app.bloodbank.controller;

import com.app.bloodbank.model.Hospital;
import com.app.bloodbank.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping()
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addHospital(Hospital hospital) {
        hospitalService.addHospital(hospital);
        return ResponseEntity.ok("Hospital added");
    }

    @PutMapping
    public ResponseEntity<String> updateHospital(Long id, Hospital updatedHospital) {
        hospitalService.updateHospital(id, updatedHospital);
        return ResponseEntity.ok("Hospital updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.ok("Hospital deleted");
    }

    @GetMapping("/{id}")
    public Hospital getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id);
    }

}
