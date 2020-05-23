package com.lxn.code.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxn.code.bean.Blog;
import com.lxn.code.bean.Tag;
import com.lxn.code.bean.Type;
import com.lxn.code.bean.User;
import com.lxn.code.dto.BlogQuery;
import com.lxn.code.service.BlogServiceImpl;
import com.lxn.code.service.TagsServiceImpl;
import com.lxn.code.service.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogServiceImpl service;
    @Autowired
    private TypeServiceImpl typeService;
    @Autowired
    private TagsServiceImpl tagsService;
    @RequestMapping("/deleteBlog")
    public String delete(Blog blog){
        service.deleteBlog(blog.getId());
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs")
    public String blogs(Model model,@RequestParam(value = "start",defaultValue = "1")int start){
        List<Type> types = typeService.listType();

        PageHelper.startPage(start,3);
        List<Blog> blogs = service.listBlog(null, null,null);
        PageInfo<Blog> info = new PageInfo<>(blogs);
        model.addAttribute("page",info);
        model.addAttribute("types",types);
        return "admin/blogs";
    }
    @GetMapping("/blogs/insert")
    public String insert(Model model){
        List<Type> types = typeService.listType();
        List<Tag> tags = tagsService.listTag();
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        return "admin/blogs-input";
    }

    @PostMapping("/blog")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes){
//        设置type的Id
        blog.setType(typeService.getType(blog.getTypeId()));
        User user = (User) session.getAttribute("user");
        blog.setUserId( user.getId());
        int i = service.saveBlog(blog);
        if (i!=0){
            attributes.addFlashAttribute("message","操作成功");
        }else {
            attributes.addFlashAttribute("message","操作失败");
        }
        return "redirect:/admin/blogs";
    }
    @PostMapping("/blogs/updates")
    public String posts(Blog blog, RedirectAttributes attributes){
        blog.setUpdateTime(new Date());
        int i = service.updateBlog(blog);
        if (i!=0){
            attributes.addFlashAttribute("message","操作成功");
        }else {
            attributes.addFlashAttribute("message","操作失败");
        }
        return "redirect:/admin/blogs";
    }
    @GetMapping("/blogs/update")
    public String update(Blog blog,Model model){
        Long id = blog.getId();
        Blog blog1 = service.getBlog(id);
        Type byName = typeService.findByName(blog1.getType().getName());
        blog1.setTypeId(byName.getId());
        List<Type> types = typeService.listType();
        List<Tag> tags = tagsService.listTag();
        model.addAttribute("blog1",blog1);
        model.addAttribute("tags",tags);
        model.addAttribute("types",types);
        return "admin/blogs-inputs";
    }


    @PostMapping("/blogs/search")
    public String search(Model model, @RequestParam(value = "start",defaultValue = "1")int start,
                         BlogQuery blogQuery){

        String title = blogQuery.getTitle();
        if (title==""){
            blogQuery.setTitle(null);
        }
        List<Type> types = typeService.listType();
        PageHelper.startPage(start,3);
        int a = 0;
        Boolean recommend = blogQuery.getRecommend();
        if (recommend==true){
            a= 1;
        }
        List<Blog> blogs = null;
        if (blogQuery.getTitle()==null||blogQuery.getRecommend()==false||blogQuery.getTypeId()==null){
            blogs = service.listBlog(null, null,null);
        }else {
            blogs = service.listBlog(blogQuery.getTitle(), blogQuery.getTypeId(), a);
        }

        PageInfo<Blog> info = new PageInfo<>(blogs);
        model.addAttribute("page",info);
        model.addAttribute("types",types);

        return "admin/blogs :: blogList";
    }


}
