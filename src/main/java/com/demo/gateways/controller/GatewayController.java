package com.demo.gateways.controller;

import com.demo.gateways.entity.Gateway;
import com.demo.gateways.service.GatewayService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gateway")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GatewayController implements BasicController<Gateway> {
    @Autowired
    GatewayService gatewayService;

    @GetMapping
    @Override
    public List<Gateway> getAll() {
        return gatewayService.getAll();
    }

    @GetMapping("/{id}")
    @Override
    public Gateway getById(@PathVariable Long id) {
        return gatewayService.getById(id).get();
    }

    @PostMapping
    @Override
    public Gateway add(@RequestBody Gateway item) {
        return gatewayService.save(item);
    }

    @PutMapping
    @Override
    public Gateway update(@RequestBody Gateway item) {
        return gatewayService.save(item);
    }

    @DeleteMapping("/{id}")
    @Override
    public boolean deleteById(@PathVariable Long id) {
        return false;
    }
}
