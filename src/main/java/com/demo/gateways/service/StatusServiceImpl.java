package com.demo.gateways.service;

import com.demo.gateways.entity.Status;
import com.demo.gateways.repository.StatusRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusServiceImpl implements StatusService{
    @Autowired
    StatusRepository statusRepo;

    @Override
    public List<Status> getAll() {
        return statusRepo.findAll();
    }

    @Override
    public Status save(Status status) {
        return statusRepo.save(status);
    }

    @Override
    public Optional<Status> getById(Long id) {
        return statusRepo.findById(id);
    }
}
