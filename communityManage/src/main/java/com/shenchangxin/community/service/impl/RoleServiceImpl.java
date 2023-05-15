package com.shenchangxin.community.service.impl;


import com.shenchangxin.community.mapper.RoleMapper;
import com.shenchangxin.community.pojo.Role;
import com.shenchangxin.community.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询角色数量
     * @return
     */
    @Override
    public Integer findAdminRoleCount(String roleName){
        return roleMapper.findAdminRoleCount(roleName);
    }


    /**
     * 根据角色名查询
     * @param roleName
     * @return
     */
    @Override
    public Role findRoleByName(String roleName){
        return roleMapper.findRoleByName(roleName);
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }
}
