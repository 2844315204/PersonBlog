package com.lxn.code.service;

import com.lxn.code.bean.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);

    List<Blog> listBlog( String title,Long typeId,Integer recommend);

    int saveBlog(Blog blog);
     int count();

    int counts(String discrib);
    List<Blog> listcount(String discrib);


    List<Blog> listBlogs();

    int updateBlog(Blog blog);

    int deleteBlog(Long id);
}
