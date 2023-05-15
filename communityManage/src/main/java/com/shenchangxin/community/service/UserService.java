package com.shenchangxin.community.service;


import com.shenchangxin.community.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    User findUserByName(String username);
    User findUserById(Integer id);
    void deleteUserById(Integer id);
    void updateUser(User user);
    void saveUser(User user);
    Map login(User user);
    void register(User user);
    List<User> getAllUser();
    List<User>  searchUserByFields(String fields);

    /**
     * 根据用户名加载用户
     */
    UserDetails loadUserByUsername(String name) throws UsernameNotFoundException;
}
