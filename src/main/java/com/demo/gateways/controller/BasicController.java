package com.demo.gateways.controller;

import org.springframework.http.ResponseEntity;

public interface BasicController<T> {
    ResponseEntity getAll();
    ResponseEntity getById(Long id);
    ResponseEntity update (T item);
    ResponseEntity add (T item);
    ResponseEntity deleteById(Long id);
}
