package com.demo.gateways.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BasicController<T> {
    List<T> getAll();
    ResponseEntity getById(Long id);
    ResponseEntity update (T item);
    ResponseEntity add (T item);
    ResponseEntity deleteById(Long id);
}
