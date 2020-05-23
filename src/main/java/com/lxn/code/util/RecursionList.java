package com.lxn.code.util;



import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecursionList {
    public static <K> List<K> getList(List<K> objectList) throws Exception{
        List<K> resultList = new LinkedList<>();
        for (K o : objectList) {
            if (StringUtils.isEmpty(getValueByField(o,"parentId"))){
                resultList.add(o);
            }
        }
        if (!CollectionUtils.isEmpty(resultList)){
            resultList.forEach(item -> {
                try {
                    List<K> childrenList = getChildrenList(getValueByField(item,"id"),objectList);
                    if (!CollectionUtils.isEmpty(childrenList)){
                        setValueByField(item,"childrenList",childrenList);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            });
        }

        return resultList;

    }


    public static <K> List<K> getChildrenList(Object parentId,List<K> objectList){
        List<K> childrenResultList = new ArrayList<>();
        objectList.forEach(item -> {
            Object pid = null;
            try {
                pid = getValueByField(item, "parentId");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (pid != null && parentId.equals(pid)){
                childrenResultList.add(item);
            }
        });

        if (!CollectionUtils.isEmpty(childrenResultList)){
            childrenResultList.forEach(item -> {
                try {
                    setValueByField(item,"childrenList",getChildrenList(getValueByField(item, "id"), objectList));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }

        if (CollectionUtils.isEmpty(childrenResultList)){
            return null;
        }

        return childrenResultList;
    }


    public static Object getValueByField(Object o,String name) throws IllegalAccessException {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0){
            for (Field declaredField : declaredFields) {
                if (name.equals(declaredField.getName())){
                    declaredField.setAccessible(true);
                    return declaredField.get(o);
                }
            }
        }
        return null;
    }


    public static void setValueByField(Object o,String name,Object value) throws IllegalAccessException {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0){
            for (Field declaredField : declaredFields) {
                if (name.equals(declaredField.getName())){
                    declaredField.setAccessible(true);
                    declaredField.set(o,value);
                }
            }
        }
    }
}
