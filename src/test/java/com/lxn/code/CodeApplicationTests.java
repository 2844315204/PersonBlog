package com.lxn.code;

import com.alibaba.druid.util.JdbcUtils;
import com.lxn.code.bean.Blog;
import com.lxn.code.bean.Comment;
import com.lxn.code.bean.Tag;
import com.lxn.code.bean.Type;
import com.lxn.code.dao.BlogDao;
import com.lxn.code.dao.CommentDao;
import com.lxn.code.service.BlogServiceImpl;
import com.lxn.code.service.TagsServiceImpl;
import com.lxn.code.service.TypeServiceImpl;
import com.lxn.code.util.RecursionList;
import org.attoparser.trace.MarkupTraceEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class CodeApplicationTests {
    @Autowired
    BlogServiceImpl service;
    @Autowired(required = false)
    BlogDao blogDao;
    @Autowired
    private TypeServiceImpl typeService;
    @Autowired
    private TagsServiceImpl tagsService;
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired(required = false)
    private CommentDao commentDao;
    @Test
    void contextLoads9() {
        List<Comment> comments = commentDao.listCommentByBlogId((long) 16);
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }
    @Test
    void contextLoads12() {
        List<Comment> comments = commentDao.listCommentByBlogId((long) 21);
        System.out.println(comments);
    }

    List<Comment> listC=new ArrayList<>();
    List<Comment> comens=new ArrayList<>();


    @Test
    void contextLoads19() throws Exception {
        List<Comment> comments = commentDao.listCommentByBlogId((long) 21);
        List<Comment> list = RecursionList.getList(comments);
        System.out.println(list);
    }


//    @Test
//    void contextLoads19() {
//        List<Comment> comments = commentDao.listCommentByBlogId((long) 21);
//        BeanUtils.copyProperties(comments,comens);
//        System.out.println(comens);
//        for (Comment comment : comments) {
//            List<Comment> byParentId = commentDao.findByParentId(comment.getId());
//            comment.setReplyComments(byParentId);
//
//            if (byParentId!=null){
//                combineChildrens(byParentId);
//            }
//        }
//        for (Comment comment : comments) {
//            System.out.println(comment.getReplyComments());
//        }

//    }
//    private void combineChildrens(List<Comment> comments) {
//        Comment comment1 = new Comment();
//        for (Comment comment : comments) {
//            listC.add(comment);
//            Long parentId = comment.getParentId();
//            List<Comment> byParentId = commentDao.findByParentId(parentId);
//            for(Comment reply1 : byParentId) {
//                recursivelys(reply1);
//            }
//            comment.setReplyComments(listC);
//            //清除临时存放区
//            this.listC = new ArrayList<>();
//        }
//
//
//    }
//
//    private void recursivelys(Comment comment){
//        List<Comment> byParentId1 = commentDao.findByParentId(comment.getId());
//        for (Comment comment2 : byParentId1) {
//            listC.add(comment2);
//        }
//
//    }

    @Test
    void contextLoads10() {
        Comment comment = new Comment();
        comment.setNickname("王五");
        comment.setEmail("2844315204@qq.com");
        comment.setContent("今天天气不错！");
        comment.setCreateTime(new Date());
        comment.setAvatar("sfag");
        comment.setBlogId((long) 16);
        int saveComment = commentDao.saveComment(comment);
        System.out.println(saveComment);

    }

    @Test
    void contextLoads6() {
        List<Blog> blogs = blogService.listBlog(null, null, null);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }
    @Test
    void contextLoads7() {
        int count = blogDao.counts("测试");
        List<Blog> listcount = blogDao.listcount("测试");
        for (Blog blog : listcount) {
            System.out.println(blog);
        }

        System.out.println(count);
    }
    
    

    @Test
    void contextLoads3() {
        List<Blog> blogs = blogService.listBlog(null, null, null);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }
    
    @Test
    void contextLoads2() {
        List<Type> types = typeService.listType();
        for (Type type : types) {
            System.out.println(type);
        }
    }
    
    @Test
    void contextLoads1() {
        List<Blog> blogs = service.listBlog(null, null,null);
        for (Blog blog1 : blogs) {
            System.out.println(blog1);
        }
    }

    @Test
    void contextLoads() {
        String s="123";
        String s1 = DigestUtils.md5DigestAsHex(s.getBytes());
        System.out.println(s1);
    }

}
