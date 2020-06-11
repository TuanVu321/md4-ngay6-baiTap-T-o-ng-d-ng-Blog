package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.blogService.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @GetMapping("/")

    public ModelAndView showBLog(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "1") int size,
                                 @RequestParam("s")Optional<String> s){
        Page<Blog> bloglist;
        Pageable pageable = new PageRequest(page,size);
        if(s.isPresent()){
            bloglist = blogService.findAllByTitContaining(s.get(),pageable);
        }
        else {
            bloglist = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("listBlog",bloglist);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("Blog",new Blog());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView updateNewBlog(@ModelAttribute("Blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }
    @GetMapping("/content/{id}")
    public ModelAndView showContent(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("content");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("Blog",blog);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("delete");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("Blog",blog);
        return modelAndView;
    }
    @PostMapping("/delete")
    public  ModelAndView delete(@ModelAttribute("Blog") Blog blog){
        blogService.delete(blog.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public  ModelAndView showEdit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("Blog",blog);
        return modelAndView;
    }
    @PostMapping("/edit")
    public  ModelAndView edit(@ModelAttribute("Blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

}
