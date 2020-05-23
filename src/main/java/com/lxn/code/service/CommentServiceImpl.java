package com.lxn.code.service;

import com.lxn.code.bean.Comment;
import com.lxn.code.dao.CommentDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired(required = false)
    private CommentDao commentDao;

    @Transactional
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> comments = commentDao.listCommentByBlogId(blogId);
        for (Comment comment : comments) {
            List<Comment> comments2 = commentDao.findByParentId(comment.getId())
            .stream()
            .filter(item -> item != null)
            .collect(Collectors.toList());

            comment.setChildrenList(comments2);
            for (Comment comment2 : comments2) {
                List<Comment> comments3 = commentDao.findByParentId(comment2.getId())
                .stream().filter(item -> item != null).collect(Collectors.toList());
                comment2.setChildrenList(comments3);
            }
        }
        return comments;
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        Long parentCommentId = comment.getParentId();
        if (parentCommentId!=-1){

            comment.setBlogId(comment.getBlogId());
        }else {
            comment.setParentId(null);
        }
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }

    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
//    private List<Comment> eachComment(List<Comment> comments) {
//        List<Comment> commentsView = new ArrayList<>();
//        for (Comment comment : comments) {
//            Comment c = new Comment();
//            BeanUtils.copyProperties(comment,c);
//            commentsView.add(c);
//        }
//        //合并评论的各层子代到第一级子代集合中
//        combineChildren(commentsView);
//        return commentsView;
//    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
//    private void combineChildren(List<Comment> comments) {
//
//        for (Comment comment : comments) {
//            List<Comment> replys1 = comment.getReplyComments();
//            for(Comment reply1 : replys1) {
//                //循环迭代，找出子代，存放在tempReplys中
//                recursively(reply1);
//            }
//            //修改顶级节点的reply集合为迭代处理后的集合
//            comment.setReplyComments(tempReplys);
//            //清除临时存放区
//            tempReplys = new ArrayList<>();
//        }
//    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
//    private void recursively(Comment comment) {
//        tempReplys.add(comment);//顶节点添加到临时存放集合
//        if (comment.getReplyComments().size()>0) {
//            List<Comment> replys = comment.getReplyComments();
//            for (Comment reply : replys) {
//                tempReplys.add(reply);
//                if (reply.getReplyComments().size()>0) {
//                    recursively(reply);
//                }
//            }
//        }
//    }

}
