package com.demo.gateways.exception;

public class LimitReachedException extends Exception {
    public LimitReachedException(String message) {
        super(message);
    }
}
