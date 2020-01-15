package com.demo.gateways.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptSingleton {
    private static BCryptPasswordEncoder ourInstance = new BCryptPasswordEncoder();

    public static BCryptPasswordEncoder getInstance() {
        if (ourInstance == null) ourInstance = new BCryptPasswordEncoder();
        return ourInstance;
    }

    private BCryptSingleton() {
    }
}