package com.shenchangxin.community.service;

import com.shenchangxin.community.pojo.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {

    void addType(Type type);

    void updateType(Type type);

    void deleteTypeById(Integer id);

    Type getTypeById(Integer id);

    List<Type> getAllType();
}
