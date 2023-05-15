package com.shenchangxin.community.service.impl;

import com.shenchangxin.community.mapper.TypeMapper;
import com.shenchangxin.community.pojo.Type;
import com.shenchangxin.community.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addType(Type type) {

        typeMapper.addType(type);

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateType(Type type) {

        typeMapper.updateType(type);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTypeById(Integer id) {

        typeMapper.deleteTypeById(id);
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeMapper.findTypeById(id);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }
}
