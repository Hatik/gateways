package com.demo.gateways.service;

import com.demo.gateways.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    List<Status> getAll();
    Status save(Status status);
    Optional<Status> getById(Long id);
}
