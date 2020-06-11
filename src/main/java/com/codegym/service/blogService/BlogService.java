package com.codegym.service.blogService;

import com.codegym.model.Blog;
import com.codegym.repository.blogRepository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;
import java.util.List;

public class BlogService implements IBlogService {
    @Autowired IBlogRepository blogRepository;

    @Override
    public Page findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public void save(Blog model) {
        blogRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public Page<Blog> findAllByTitContaining(String tit, Pageable pageable) {
        return blogRepository.findAllByTitContaining(tit,pageable);
    }
}
