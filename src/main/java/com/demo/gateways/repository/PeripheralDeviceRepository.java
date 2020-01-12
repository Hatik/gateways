package com.demo.gateways.repository;

import com.demo.gateways.entity.Gateway;
import com.demo.gateways.entity.PeripheralDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeripheralDeviceRepository extends JpaRepository<PeripheralDevice, Long> {
    List<PeripheralDevice> getAllByGateway(Gateway gateway);
}
