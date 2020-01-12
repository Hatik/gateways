package com.demo.gateways.repository;

import com.demo.gateways.entity.PeripheralDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeripheralDeviceRepository extends JpaRepository<PeripheralDevice, Long> {
}
