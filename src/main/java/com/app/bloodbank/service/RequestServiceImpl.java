package com.app.bloodbank.service;

import com.app.bloodbank.model.BloodInventory;
import com.app.bloodbank.model.Request;
import com.app.bloodbank.model.Status;
import com.app.bloodbank.repository.BloodBankRepository;
import com.app.bloodbank.repository.BloodInventoryRepository;
import com.app.bloodbank.repository.BloodTypeRepository;
import com.app.bloodbank.repository.RequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final BloodInventoryRepository bloodInventoryRepository;

    public RequestServiceImpl(RequestRepository requestRepository, BloodInventoryRepository bloodInventoryRepository) {
        this.requestRepository = requestRepository;
        this.bloodInventoryRepository = bloodInventoryRepository;
    }

    @Override
    public void createRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public void updateRequestStatus(Long id, Status newStatus) {
        Request request = getRequestById(id);
        request.setStatus(newStatus);
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Request not found with ID: " + id));
    }

    @Transactional
    @Override
    public void processRequest(Long requestId) {
        Request request = getRequestById(requestId);
        double requestedLiters = request.getQuantityInLiters();
        Long bloodBankId = request.getBloodBank().getId();
        Long bloodTypeId = request.getBloodType().getId();

        Optional<BloodInventory> inventoryOpt = bloodInventoryRepository.findByBloodBankIdAndBloodTypeId(bloodBankId, bloodTypeId);

        if (inventoryOpt.isPresent()) {
            BloodInventory inventory = inventoryOpt.get();
            if (inventory.getQuantityInLiters() >= requestedLiters) {
                // if inventory has enough blood available
                inventory.setQuantityInLiters(inventory.getQuantityInLiters() - requestedLiters);
                bloodInventoryRepository.save(inventory);

                request.setStatus(Status.COMPLETED);
            } else {
                // not enough blood
                request.setStatus(Status.DENIED);
            }
        }
        else {
            // if inventory doesn't exist
            request.setStatus(Status.DENIED);
        }

        requestRepository.save(request);
    }
}
