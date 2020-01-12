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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/gateway")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GatewayController implements BasicController<Gateway> {
    @Autowired
    GatewayService gatewayService;
    @Autowired
    PeripheralDeviceService peripheralDeviceService;

    @GetMapping
    @Override
    public List<Gateway> getAll() {
        return gatewayService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity getById(@PathVariable Long id) {
        Optional<Gateway> gateway = gatewayService.getById(id);
        if (gateway.isPresent()) return ResponseEntity.ok(gateway.get());
        return ResponseEntity.notFound().build();
    }

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

    @PutMapping
    @Override
    public ResponseEntity update(@RequestBody Gateway item) {
        try {
            return ResponseEntity.ok(gatewayService.save(item));
        } catch (IpV4AddressFormatException | SerialNumberUniquenessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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
