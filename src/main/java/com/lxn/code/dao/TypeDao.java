package com.lxn.code.dao;

import com.lxn.code.bean.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeDao {
    int saveType(Type type);

    Type findByName(@Param("name") String name);

    Type getType(Long id);

    int updateType(@Param("id") Long id, @Param("name") String name);

    int deleteType(Long id);

    List<Type> listType();
}
