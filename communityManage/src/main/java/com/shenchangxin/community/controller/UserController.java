package com.shenchangxin.community.controller;

import com.shenchangxin.community.entity.Result;
import com.shenchangxin.community.entity.StatusCode;
import com.shenchangxin.community.pojo.User;
import com.shenchangxin.community.service.UserService;
import com.shenchangxin.community.utils.FormatUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FormatUtil formatUtil;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (!formatUtil.checkStringNull(
                user.getName(),
                user.getUsername(),
                user.getPassword())){
            return Result.create(StatusCode.ERROR, "注册失败，字段不完整");
        }
        try {
            userService.register(user);
            return Result.create(StatusCode.OK, "注册成功");
        } catch (RuntimeException e) {
            return Result.create(StatusCode.ERROR, "注册失败，" + e.getMessage());
        }
    }

    /**
     * 登录返回token
     */
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (!formatUtil.checkStringNull(user.getUsername(), user.getPassword())) {
            return Result.create(StatusCode.ERROR, "参数错误");
        }

        try {
            Map map = userService.login(user);
            return Result.create(StatusCode.OK, "登录成功",map);
        } catch (UsernameNotFoundException unfe) {
            return Result.create(StatusCode.LOGINERROR, "登录失败，用户名或密码错误");
        } catch (RuntimeException re) {
            return Result.create(StatusCode.LOGINERROR, re.getMessage());
        }

    }


}
