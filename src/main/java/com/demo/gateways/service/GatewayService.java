package com.demo.gateways.service;

import com.demo.gateways.entity.Gateway;
import com.demo.gateways.exception.IpV4AddressFormatException;
import com.demo.gateways.exception.SerialNumberUniquenessException;

import java.util.List;
import java.util.Optional;

public interface GatewayService {
    Gateway save(Gateway gateway) throws IpV4AddressFormatException, SerialNumberUniquenessException;
    List<Gateway> getAll();
    Optional<Gateway> getById(Long id);
    void deleteById(Long id);
}
