package com.codegym.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface service <T> {
    Page <T> findAll(Pageable pageable);
    T findById(Long id);
    void save (T model);
    void delete (Long id);
}
