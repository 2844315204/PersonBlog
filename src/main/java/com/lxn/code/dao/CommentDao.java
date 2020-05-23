package com.lxn.code.dao;

import com.lxn.code.bean.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    List<Comment>

    listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);

    Comment findById(Long id);
    List<Comment> findByParentId(Long id);
}
