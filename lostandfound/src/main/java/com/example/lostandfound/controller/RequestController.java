package com.example.lostandfound.controller;

import com.example.lostandfound.entity.Request;
import com.example.lostandfound.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestService requestService;

    @GetMapping
    public List<Request> getAll() {
        logger.info("Fetching all requests");
        return requestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable Long id) {
        logger.info("Fetching request with ID: {}", id);
        return requestService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Request not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public Request create(@RequestBody Request request) {
        logger.info("Creating request for item ID: {}", request.getItem().getId());
        return requestService.save(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Request> update(@PathVariable Long id, @RequestBody Request request) {
        logger.info("Updating request with ID: {}", id);
        return requestService.findById(id)
                .map(existing -> {
                    request.setId(id);
                    return ResponseEntity.ok(requestService.save(request));
                })
                .orElseGet(() -> {
                    logger.warn("Request not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting request with ID: {}", id);
        requestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}