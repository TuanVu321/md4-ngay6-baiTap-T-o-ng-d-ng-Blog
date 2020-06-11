package com.codegym.service.blogService;

import com.codegym.model.Blog;
import com.codegym.service.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IBlogService extends service <Blog>{
    Page<Blog> findAllByTitContaining(String tit, Pageable pageable);
}
