package com.lxn.code.service;

import com.lxn.code.bean.Blog;
import com.lxn.code.controller.NotFoundException;
import com.lxn.code.dao.BlogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Override
    public List<Blog> listBlog(String title,Long typeId,Integer recommend) {
        return blogDao.listBlog(title,typeId,recommend);
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogDao.saveBlog(blog);
    }

    @Override
    public int count() {
        return blogDao.count();
    }

    @Override
    public int counts(String discrib) {
        return blogDao.counts(discrib);
    }

    @Override
    public List<Blog> listcount(String discrib) {
        return blogDao.listcount(discrib);
    }

    @Override
    public List<Blog> listBlogs() {
        return blogDao.listBlogs();
    }

    @Override
    public int updateBlog(Blog blog) {
        Blog b = blogDao.getBlog(blog.getId());
        if (b==null){
            throw new NotFoundException("该博客不存在！");
        }
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }
}
