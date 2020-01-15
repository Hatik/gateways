package com.demo.gateways.controller;

import com.demo.gateways.entity.Gateway;
import com.demo.gateways.entity.PeripheralDevice;
import com.demo.gateways.exception.IpV4AddressFormatException;
import com.demo.gateways.exception.LimitReachedException;
import com.demo.gateways.exception.NotFoundException;
import com.demo.gateways.exception.SerialNumberUniquenessException;
import com.demo.gateways.service.GatewayService;
import com.demo.gateways.service.PeripheralDeviceService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/gateway")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GatewayController implements BasicController<Gateway> {
    //TODO add Security
    @Autowired
    GatewayService gatewayService;
    @Autowired
    PeripheralDeviceService peripheralDeviceService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @Override
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(gatewayService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    @Override
    public ResponseEntity getById(@PathVariable Long id) {
        Optional<Gateway> gateway = gatewayService.getById(id);
        if (gateway.isPresent()) return ResponseEntity.ok(gateway.get());
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @Override
    public ResponseEntity add(@RequestBody Gateway item) {
        try {
            return ResponseEntity.ok(gatewayService.save(item));
        } catch (IpV4AddressFormatException | SerialNumberUniquenessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/peripheralDevices")
    public ResponseEntity getAllDevices(@PathVariable Long id) {
        Optional<Gateway> gateway = gatewayService.getById(id);
        if (gateway.isPresent()) return ResponseEntity.ok(peripheralDeviceService.getByGateway(gateway.get()));
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    @Override
    public ResponseEntity update(@RequestBody Gateway item) {
        try {
            return ResponseEntity.ok(gatewayService.save(item));
        } catch (IpV4AddressFormatException | SerialNumberUniquenessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            gatewayService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/addPeripheralDevice")
    public ResponseEntity addPeripheralDevice(@PathVariable Long id, @RequestBody PeripheralDevice device) {
        try {
            PeripheralDevice result = peripheralDeviceService.addPeripheralDeviceToGateway(id, device);
            return ResponseEntity.ok(result);
        } catch (LimitReachedException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
