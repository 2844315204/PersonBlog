package com.lxn.code.dao;

import com.lxn.code.bean.Tag;
import com.lxn.code.bean.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagsDao {
    int saveTag(Tag type);

    Tag findByName(@Param("name") String name);

    Tag getTag(Long id);

    int updateTag(@Param("id") Long id, @Param("name") String name);

    int deleteTag(Long id);

    List<Tag> listType();
}
