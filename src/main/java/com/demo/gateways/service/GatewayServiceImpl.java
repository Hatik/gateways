package com.demo.gateways.service;

import com.demo.gateways.entity.Gateway;
import com.demo.gateways.repository.GatewayRepository;
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
public class GatewayServiceImpl implements GatewayService {
    @Autowired
    GatewayRepository gatewayRepo;

    @Override
    public Gateway save(Gateway gateway) {

        return gatewayRepo.save(gateway);
    }

    @Override
    public List<Gateway> getAll() {
        return gatewayRepo.findAll();
    }

    @Override
    public Optional<Gateway> getById(Long id) {
        return gatewayRepo.findById(id);
    }
}
