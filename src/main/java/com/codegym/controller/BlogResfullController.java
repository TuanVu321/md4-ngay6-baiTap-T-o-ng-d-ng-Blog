package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.blogService.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogResfullController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("/showBlog")
    public ResponseEntity<Page<Blog>> listAllBlog(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "1") int size){
        Pageable pageable = new PageRequest(page,size);
        Page<Blog> bloglist = blogService.findAll(pageable);
        if(bloglist.hasNext()){
            return new ResponseEntity<Page<Blog>>(bloglist,HttpStatus.OK);
        }
        return new ResponseEntity<Page<Blog>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/content/{id}")
    public ResponseEntity<Blog> showBlog(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if(blog==null){
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Blog>(blog,HttpStatus.OK);
    }
    @PutMapping("/content/{id}")
    public ResponseEntity<Blog> editBlog(@PathVariable Long id,@RequestBody Blog blog){
        Blog blog1 = blogService.findById(blog.getId());
        if(blog1==null){
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        blog1.setId(blog.getId());
        blog1.setCategory(blog.getCategory());
        blog1.setContent(blog.getContent());
        blog1.setName(blog.getName());
        blog1.setTit(blog.getTit());
        blogService.save(blog1);
        return new ResponseEntity<Blog>(blog1,HttpStatus.OK);

    }



}
