package com.demo.gateways.controller;

import java.util.List;

public interface BasicController<T> {
    List<T> getAll();
    T getById(Long id);
    T update (T item);
    T add (T item);
    boolean deleteById(Long id);
}
