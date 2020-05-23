package com.lxn.code.bean;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private Long id;
    private Long parentId;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Long blogId;
    private Blog blog;
    private List<Comment> childrenList ;


}
