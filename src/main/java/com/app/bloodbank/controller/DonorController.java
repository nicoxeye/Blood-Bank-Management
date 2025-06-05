package com.app.bloodbank.controller;

import com.app.bloodbank.model.Address;
import com.app.bloodbank.model.BloodType;
import com.app.bloodbank.model.Donor;
import com.app.bloodbank.repository.AddressRepository;
import com.app.bloodbank.repository.DonorRepository;
import com.app.bloodbank.service.DonorService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService donorService;
    private final AddressRepository addressRepository;
    private final DonorRepository donorRepository;

    public DonorController(DonorService donorService, AddressRepository addressRepository, DonorRepository donorRepository) {
        this.donorService = donorService;
        this.addressRepository = addressRepository;
        this.donorRepository = donorRepository;
    }

    @GetMapping("")
    public List<Donor> getDonors() {
        return donorService.getAllDonors();
    }

    @PostMapping
    public ResponseEntity<String> addDonor(@RequestBody Donor donor) {

        Address incomingAddress = donor.getAddress();

        // finding an address with the same metadata
        Optional<Address> existingAddress = addressRepository.findByCountryAndCityAndStreetAndZipcode(
                incomingAddress.getCountry(),
                incomingAddress.getCity(),
                incomingAddress.getStreet(),
                incomingAddress.getZipcode());

        Address finalAddress = existingAddress.orElseGet(() -> addressRepository.save(incomingAddress));

        donor.setAddress(finalAddress);

        donorService.addDonor(donor);
        return ResponseEntity.ok("Donor added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDonor(@PathVariable Long id, @RequestBody Donor updatedDonorData) {

        Address incomingAddress = updatedDonorData.getAddress();

        // finding an address with the same metadata
        Optional<Address> existingAddress = addressRepository.findByCountryAndCityAndStreetAndZipcode(
                incomingAddress.getCountry(),
                incomingAddress.getCity(),
                incomingAddress.getStreet(),
                incomingAddress.getZipcode());

        Address finalAddress = existingAddress.orElseGet(() -> addressRepository.save(incomingAddress));

        updatedDonorData.setAddress(finalAddress);

        donorService.updateDonor(id, updatedDonorData);
        return ResponseEntity.ok("Donor updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return ResponseEntity.ok("Donor deleted successfully");
    }

    @GetMapping("/{id}")
    public Donor getDonorById(@PathVariable Long id) {
        return donorService.getDonorById(id);
    }


    @GetMapping("/search")
    public List<Donor> searchDonors(@RequestParam(required = false) String surname, @RequestParam(required = false) String city) {
        if (surname != null) return donorRepository.findBySurnameContainingIgnoreCase(surname);
        else if (city != null) return donorRepository.findByCityContainingIgnoreCase(city);

        return donorRepository.findAll();
    }

    @GetMapping("/sorted")
    public List<Donor> getDonorsSorted(@RequestParam(defaultValue = "name") String sortBy) {
        return donorRepository.findAll(Sort.by(sortBy));
    }

}
