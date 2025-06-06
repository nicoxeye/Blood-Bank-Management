package com.app.bloodbank.service;

import com.app.bloodbank.model.Hospital;

import java.util.List;

public interface HospitalService {
    public abstract List<Hospital> getAllHospitals();
    public abstract void addHospital(Hospital hospital);
    public abstract void updateHospital(Long id, Hospital updatedData);
    public abstract void deleteHospital(Long id);
    public abstract Hospital getHospitalById(Long id);

    List<Hospital> findHospitalByCity(String city);
}
