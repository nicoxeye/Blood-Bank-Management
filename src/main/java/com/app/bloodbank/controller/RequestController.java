package com.app.bloodbank.controller;

import com.app.bloodbank.model.Request;
import com.app.bloodbank.model.Status;
import com.app.bloodbank.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("")
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @PostMapping
    public ResponseEntity<String> createRequest(@RequestBody Request request) {
        requestService.createRequest(request);
        return ResponseEntity.ok("Request created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRequestStatus(@PathVariable Long id, Status newStatus) {
        requestService.updateRequestStatus(id, newStatus);
        return ResponseEntity.ok("Request updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.ok("Request deleted");
    }

    @GetMapping("/{id}")
    public Request getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
    }
}
