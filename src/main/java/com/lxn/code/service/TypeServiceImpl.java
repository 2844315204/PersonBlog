package com.lxn.code.service;

import com.lxn.code.bean.Type;
import com.lxn.code.controller.NotFoundException;
import com.lxn.code.dao.TypeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Resource
    private TypeDao typeDao;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type findByName(String name) {
        return typeDao.findByName(name);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Transactional
    @Override
    public int updateType(Long id, String name) {
        Type t = typeDao.getType(id);
        if (t==null){
            throw new NotFoundException("不存在该类型");
        }else {
            int type= typeDao.updateType(id, name);
            return type;
        }
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
       return  typeDao.deleteType(id);
    }

    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }
}
