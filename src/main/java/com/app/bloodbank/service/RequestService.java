package com.app.bloodbank.service;

import com.app.bloodbank.model.Request;
import com.app.bloodbank.model.Status;

import java.util.List;

public interface RequestService {
    public abstract void createRequest(Request request);
    public abstract void updateRequestStatus(Long id, Status newStatus);
    public abstract void deleteRequest(Long id);

    public abstract List<Request> getAllRequests();
    public abstract Request getRequestById(Long id);

    // for automatic deduction from inventory if available;
    public abstract void processRequest(Long requestId);
}
