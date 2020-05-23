package com.lxn.code.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Blog implements Serializable {
    private Long id;
    private String title;//标题
    private String content;//内容
    private String firstPicture;//首图（第一次图）
    private String flag ;//标记：原创、转载、翻译
    private Integer views;//浏览次数
    private boolean appreciation;//赞赏是否开启
    private boolean shareStatement;//版权是否开启
    private boolean commentabled;//是否开启评论
    private boolean published;//是否发布
    private boolean recommend;//是否推荐
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private Long typeId;
    private Type type;
    private Long userId;
    private User user;
    private String tag;
}
