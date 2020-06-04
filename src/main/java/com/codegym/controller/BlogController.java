package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.blogService.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @GetMapping("/")
    public ModelAndView showBLog(){
        ModelAndView modelAndView = new ModelAndView("home");
        List<Blog> List = blogService.findAll();
        modelAndView.addObject("listBlog",List);
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
        ModelAndView modelAndView = new ModelAndView("home");
        blogService.save(blog);
        List<Blog> List = blogService.findAll();
        modelAndView.addObject("listBlog",List);
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
        ModelAndView modelAndView = new ModelAndView("home");
        blogService.delete(blog.getId());
        List<Blog> List = blogService.findAll();
        modelAndView.addObject("listBlog",List);
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
        ModelAndView modelAndView = new ModelAndView("home");
        blogService.save(blog);
        List<Blog> List = blogService.findAll();
        modelAndView.addObject("listBlog",List);
        return modelAndView;
    }

}
