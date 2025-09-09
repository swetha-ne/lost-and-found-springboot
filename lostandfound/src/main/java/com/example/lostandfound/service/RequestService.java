package com.example.lostandfound.service;

import com.example.lostandfound.entity.Request;
import com.example.lostandfound.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);

    @Autowired
    private RequestRepository requestRepository;

    public Request save(Request request) {
        logger.info("Saving request for item ID: {}", request.getItem().getId());
        return requestRepository.save(request);
    }

    public List<Request> findAll() {
        logger.info("Fetching all requests");
        return requestRepository.findAll();
    }

    public Optional<Request> findById(Long id) {
        logger.info("Fetching request with ID: {}", id);
        return requestRepository.findById(id);
    }

    public void deleteById(Long id) {
        logger.info("Deleting request with ID: {}", id);
        requestRepository.deleteById(id);
    }
}