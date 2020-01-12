package com.demo.gateways.bootstrap;

import com.demo.gateways.entity.Status;
import com.demo.gateways.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired private StatusRepository statusRepository;

    @Override
    public void run(String... args) throws Exception {
        Status online = Status.builder().name("Online").build();
        Status offline = Status.builder().name("Offline").build();
        statusRepository.save(online);
        statusRepository.save(offline);
    }
}
