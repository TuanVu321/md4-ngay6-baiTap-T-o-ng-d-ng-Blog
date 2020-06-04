package com.codegym.service;

import java.util.List;

public interface service <T> {
    List <T> findAll();
    T findById(Long id);
    void save (T model);
    void delete (Long id);
}
