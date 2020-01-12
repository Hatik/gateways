package com.demo.gateways.service;

import com.demo.gateways.entity.Gateway;

import java.util.List;
import java.util.Optional;

public interface GatewayService {
    Gateway save(Gateway gateway);
    List<Gateway> getAll();
    Optional<Gateway> getById(Long id);
}
