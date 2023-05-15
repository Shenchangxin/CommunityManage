package com.shenchangxin.community.service;



import com.shenchangxin.community.pojo.Role;

import java.util.List;

public interface RoleService {

    Integer findAdminRoleCount(String roleName);


    /**
     * 根据角色名查询
     */
    Role findRoleByName(String roleName);

    /**
     * 查询所有角色
     */
    List<Role> findAllRole();
}