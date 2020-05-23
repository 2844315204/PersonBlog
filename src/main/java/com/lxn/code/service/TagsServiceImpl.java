package com.lxn.code.service;

import com.lxn.code.bean.Tag;
import com.lxn.code.bean.Type;
import com.lxn.code.controller.NotFoundException;
import com.lxn.code.dao.TagsDao;
import com.lxn.code.dao.TypeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagsServiceImpl implements TagsService{

    @Resource
    private TagsDao tagsDao;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagsDao.saveTag(tag);
    }

    @Override
    public Tag findByName(String name) {
        return tagsDao.findByName(name);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagsDao.getTag(id);
    }

    @Transactional
    @Override
    public int updateTag(Long id, String name) {

        Tag tag = tagsDao.getTag(id);
        if (tag==null){
            throw new NotFoundException("不存在该类型");
        }else {
            int dbtag= tagsDao.updateTag(id, name);
            return dbtag;
        }
    }

    @Transactional
    @Override
    public int deleteTag(Long id) {
        return tagsDao.deleteTag(id);
    }

    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagsDao.listType();
    }


}
