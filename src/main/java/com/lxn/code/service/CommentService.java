package com.lxn.code.service;

import com.lxn.code.bean.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);
}
