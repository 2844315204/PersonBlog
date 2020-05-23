package com.lxn.code.service;

import com.lxn.code.bean.Tag;
import com.lxn.code.bean.Type;

import java.util.List;

public interface TagsService {
    int saveTag(Tag tag);
    Tag findByName(String name);

    Tag getTag(Long id);

    int updateTag(Long id, String name);

    int deleteTag(Long id);

    List<Tag> listTag();
}
