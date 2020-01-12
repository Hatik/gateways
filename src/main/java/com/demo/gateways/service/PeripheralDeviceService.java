package com.demo.gateways.service;

import com.demo.gateways.entity.Gateway;
import com.demo.gateways.entity.PeripheralDevice;
import com.demo.gateways.exception.LimitReachedException;
import com.demo.gateways.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface PeripheralDeviceService {
    List<PeripheralDevice> getAll();
    PeripheralDevice save(PeripheralDevice device);
    Optional<PeripheralDevice> getById(Long id);
    PeripheralDevice addPeripheralDeviceToGateway(Long gatewayId, PeripheralDevice device) throws NotFoundException, LimitReachedException;
    List<PeripheralDevice> getByGateway(Gateway gateway);
    void deleteById(Long id);
}
