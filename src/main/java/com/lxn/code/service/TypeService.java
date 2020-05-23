package com.lxn.code.service;

import com.lxn.code.bean.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeService {
    int saveType(Type type);
    Type findByName(String name);

    Type getType(Long id);

    int updateType(Long id,String name);

    int deleteType(Long id);

    List<Type> listType();
}
