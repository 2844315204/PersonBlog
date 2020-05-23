package com.lxn.code.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxn.code.bean.Blog;
import com.lxn.code.bean.Comment;
import com.lxn.code.bean.Tag;
import com.lxn.code.bean.Type;
import com.lxn.code.service.BlogServiceImpl;
import com.lxn.code.service.CommentServiceImpl;
import com.lxn.code.service.TagsServiceImpl;
import com.lxn.code.service.TypeServiceImpl;
import com.lxn.code.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class BlogUserController {
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private TypeServiceImpl typeService;
    @Autowired
    private TagsServiceImpl tagsService;
    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/blog/{id}")
    public String blogs(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlog(id);
        if (blog==null){
            throw new NotFoundException("该博客不存在");
        }
        Blog blog1 = new Blog();
        BeanUtils.copyProperties(blog,blog1);
        String content = blog1.getContent();
        blog1.setContent(MarkdownUtils.markdownToHtml(content));
        List<Comment> comments = commentService.listCommentByBlogId(id);

        model.addAttribute("comments", comments);
        model.addAttribute("blog",blog1);
        return "blogs";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/archives")
    public String archives(){
        return "archives";
    }
    @GetMapping("/index")
    public String index(Model model,@RequestParam(value = "start",defaultValue = "1")int start){
        int count = blogService.count();
        PageHelper.startPage(start,3);
        List<Blog> blogs = blogService.listBlogs();
        PageInfo<Blog> info = new PageInfo<>(blogs);
        List<Type> types = typeService.listType();
        List<Tag> tags = tagsService.listTag();

        model.addAttribute("info",info);
        model.addAttribute("types",types);
        model.addAttribute("count",count);
        model.addAttribute("tags",tags);
        return "index";
    }
    @PostMapping("/search")
    public String indexpost(Model model,
                            @RequestParam(value = "start",defaultValue = "1")int start,
                            @RequestParam String searchcontent){
        int count = blogService.counts(searchcontent);
        PageHelper.startPage(start,3);

        List<Blog> listcount = blogService.listcount(searchcontent);
        PageInfo<Blog> info = new PageInfo<>(listcount);
        List<Type> types = typeService.listType();
        List<Tag> tags = tagsService.listTag();

        model.addAttribute("info",info);
        model.addAttribute("types",types);
        model.addAttribute("count",count);
        model.addAttribute("tags",tags);
        return "search";
    }
    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }
    @GetMapping("/types")
    public String types(){
        return "types";
    }

}
