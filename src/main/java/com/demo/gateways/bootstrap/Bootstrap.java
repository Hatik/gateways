package com.demo.gateways.bootstrap;

import com.demo.gateways.config.BCryptSingleton;
import com.demo.gateways.entity.Role;
import com.demo.gateways.entity.Status;
import com.demo.gateways.entity.User;
import com.demo.gateways.repository.RoleRepository;
import com.demo.gateways.repository.StatusRepository;
import com.demo.gateways.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired private StatusRepository statusRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        //TODO rewrite using migrations
        Status online = Status.builder().name("Online").build();
        Status offline = Status.builder().name("Offline").build();
        statusRepository.save(online);
        statusRepository.save(offline);

        Role roleAdmin = new Role((long) 1, "ADMIN");
        Role roleUser = new Role((long) 2, "USER");
        User user = User.builder()
                .id(1L)
                .email("w.askhat.serikov@gmail.com")
                .password(BCryptSingleton.getInstance().encode("123"))
                .active(1)
                .roles(Arrays.asList(roleAdmin, roleUser)).build();

        userRepository.save(user);
    }
}
