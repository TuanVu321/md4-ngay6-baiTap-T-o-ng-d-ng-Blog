package com.codegym.service.blogService;

import com.codegym.model.Blog;
import com.codegym.repository.blogRepository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.util.List;

public class BlogService implements IBlogService {
    @Autowired IBlogRepository blogRepository;

    @Override
    public List findAll() {
        return blogRepository.findAll();
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
}
