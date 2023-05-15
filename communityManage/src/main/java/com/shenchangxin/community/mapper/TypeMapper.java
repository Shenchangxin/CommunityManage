package com.shenchangxin.community.mapper;

import com.shenchangxin.community.pojo.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {

    Type findTypeById(Integer id);

    Type findTypeByName(String typeName);

    void addType(Type type);

    void deleteTypeById(Integer id);

    void updateType(Type type);

    List<Type> getAllType();

}
