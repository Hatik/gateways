package com.demo.gateways.exception;

public class SerialNumberUniquenessException extends Exception {
    static final String message = "SerialNumber must be unique";
    public SerialNumberUniquenessException() {
        super(message);
    }


}
