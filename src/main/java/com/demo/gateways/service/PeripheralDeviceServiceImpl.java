package com.demo.gateways.service;

import com.demo.gateways.entity.Gateway;
import com.demo.gateways.entity.PeripheralDevice;
import com.demo.gateways.exception.LimitReachedException;
import com.demo.gateways.exception.NotFoundException;
import com.demo.gateways.repository.PeripheralDeviceRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PeripheralDeviceServiceImpl implements PeripheralDeviceService {
    @Autowired
    PeripheralDeviceRepository deviceRepo;
    @Autowired
    GatewayService gatewayService;
    @Value("${maximum.peripheral.device.per.gateway}")
    public String maxNumberPerGateway;

    @Override
    public List<PeripheralDevice> getAll() {
        return deviceRepo.findAll();
    }

    @Override
    public PeripheralDevice save(PeripheralDevice device) {
        return deviceRepo.save(device);
    }

    @Override
    public Optional<PeripheralDevice> getById(Long id) {
        return deviceRepo.findById(id);
    }

    @Override
    public PeripheralDevice addPeripheralDeviceToGateway(Long id, PeripheralDevice device) throws NotFoundException, LimitReachedException {
        Optional<Gateway> gatewayOptional = gatewayService.getById(id);
        if (!gatewayOptional.isPresent()) throw new NotFoundException("gateway not found");
        Gateway gateway = gatewayOptional.get();
        return addToGateway(gateway, device);
    }

    public PeripheralDevice addToGateway(Gateway gateway, PeripheralDevice device) throws LimitReachedException {
        List<PeripheralDevice> devices = getByGateway(gateway);
        if(devices.size() >= Integer.valueOf(maxNumberPerGateway))
            throw new LimitReachedException("max limit reached");
        device.setGateway(gateway);
        return save(device);
    }

    @Override
    public List<PeripheralDevice> getByGateway(Gateway gateway) {
        return deviceRepo.getAllByGateway(gateway);
    }

    @Override
    public void deleteById(Long id) {
        deviceRepo.deleteById(id);
    }
}
