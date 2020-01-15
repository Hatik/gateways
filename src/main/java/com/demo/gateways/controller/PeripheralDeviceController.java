package com.demo.gateways.controller;

import com.demo.gateways.entity.PeripheralDevice;
import com.demo.gateways.service.PeripheralDeviceService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/peripheralDevice")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PeripheralDeviceController implements BasicController<PeripheralDevice> {
    @Autowired
    PeripheralDeviceService deviceService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(deviceService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        Optional<PeripheralDevice> gateway = deviceService.getById(id);
        if (gateway.isPresent()) return ResponseEntity.ok(gateway.get());
        return ResponseEntity.notFound().build();
    }

    @Override
    @PutMapping
    public ResponseEntity update(@RequestBody PeripheralDevice item) {
        try {
            return ResponseEntity.ok(deviceService.save(item));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    @PostMapping
    public ResponseEntity add(@RequestBody PeripheralDevice item) {
        try {
            return ResponseEntity.ok(deviceService.save(item));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            deviceService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
