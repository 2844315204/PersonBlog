package com.lxn.code.dao;

import com.lxn.code.bean.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogDao {

    Blog getBlog(@Param("id") Long id);

    List<Blog> listBlog(@Param("title") String title,@Param("typeId") Long typeId,@Param("recommend")Integer recommend);
    List<Blog> listBlogs();

    List<Blog> selectAll();

    int saveBlog(Blog blog);

    int count();
    int counts(String discrib);
    List<Blog> listcount(String discrib);
    int updateBlog(Blog blog);

    int deleteBlog(Long id);

}
