package com.demo.gateways.controller;

import com.demo.gateways.entity.Status;
import com.demo.gateways.service.StatusService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/status")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusController {
    @Autowired
    StatusService statusService;

    @GetMapping
    public List<Status> getAll() {
        return statusService.getAll();
    }
}
