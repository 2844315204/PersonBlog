package com.lxn.code.controller;

import com.lxn.code.bean.Comment;
import com.lxn.code.service.BlogServiceImpl;
import com.lxn.code.service.CommentService;
import com.lxn.code.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private BlogServiceImpl blogService;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){

        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blogs :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment){
        Long blogId = comment.getBlogId();
        comment.setBlog(blogService.getBlog(blogId));
        commentService.saveComment(comment);
        return "redirect:/user/comments/"+blogId;
    }
}
