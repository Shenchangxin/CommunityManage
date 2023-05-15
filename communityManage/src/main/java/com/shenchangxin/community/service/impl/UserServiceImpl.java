package com.shenchangxin.community.service.impl;

import com.shenchangxin.community.config.JwtConfig;
import com.shenchangxin.community.mapper.RoleMapper;
import com.shenchangxin.community.mapper.UserMapper;
import com.shenchangxin.community.pojo.Role;
import com.shenchangxin.community.pojo.User;
import com.shenchangxin.community.service.RoleService;
import com.shenchangxin.community.service.UserService;
import com.shenchangxin.community.utils.JsonWebTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JsonWebTokenUtil jsonWebTokenUtil;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByUsername(username);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    /**
     * 根据id删除用户
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    /**
     * 更新用户
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<User>  searchUserByFields(String fields){
        return userMapper.searchUserByFields(fields);
    }

    /**
     * 保存用户
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

    /**
     * 登录
     * 返回token，用户名，用户角色
     */
    @Override
    public Map login(User user) throws UsernameNotFoundException, RuntimeException {

        User dbUser = this.findUserByName(user.getUsername());
        //此用户不存在 或 密码错误
        if (null == dbUser || !encoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }


        //用户名 密码 匹配 签发token
        final UserDetails userDetails = this.loadUserByUsername(user.getUsername());

        final String token = jsonWebTokenUtil.generateToken(userDetails);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }

        Map<String, Object> map = new HashMap<>(3);

        map.put("token", jwtConfig.getPrefix() + token);
        map.put("name", user.getUsername());
        map.put("roles", roles);
        return map;
    }

    /**
     * 注册
     *
     * @param
     */
    @Transactional(rollbackFor = Exception.class)
    public void register(User userToAdd) throws RuntimeException {

        //有效 保存用户
        final String username = userToAdd.getUsername();
        if (userMapper.findUserByUsername(username) != null) {
            throw new RuntimeException("用户名已存在");
        }
        List<Role> roles = new ArrayList<>(1);
        roles.add(roleService.findRoleByName("USER"));
        userToAdd.setRoles(roles);//新注册用户赋予USER权限

        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));//加密密码
        userMapper.saveUser(userToAdd);//保存角色
        //保存该用户的所有角色
        for (Role role : roles) {
            roleMapper.saveUserRoles(userToAdd.getId(), role.getId());
        }
    }

    /**
     * 根据用户名查询用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(1);
        //用于添加用户的权限。将用户权限添加到authorities
        List<Role> roles = roleMapper.findUserRoles(user.getId());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), "***********", authorities);
    }


}
