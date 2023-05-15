package com.shenchangxin.community.mapper;

import com.shenchangxin.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    void saveUser(User user);
    void deleteUserById(Integer id);
    User findUserById(Integer id);
    void updateUser(User user);
    User findUserByUsername(String username);
    List<User> getAllUser();
    List<User> searchUserByFields(@Param(value="fields")String fields);

}
