package com.shenchangxin.community.controller;

import com.shenchangxin.community.entity.Result;
import com.shenchangxin.community.entity.StatusCode;
import com.shenchangxin.community.pojo.Community;
import com.shenchangxin.community.pojo.User;
import com.shenchangxin.community.service.CommunityService;
import com.shenchangxin.community.service.UserService;
import com.shenchangxin.community.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @Autowired
    private FormatUtil formatUtil;


    @GetMapping("/getCommunity")
    public Result getCommunity(){

        try{
            List<Community> communities = communityService.getAllCommunity();
            return Result.create(StatusCode.OK,"查询成功",communities);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR, "获取信息失败" + e.getMessage());
        }

    }


    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/addCommunity")
    public Result addCommunity(@RequestParam(value = "name") String name,@RequestParam("description") String description,@RequestParam("type") String type){

        if(!formatUtil.checkObjectNull(name,type,description)){
            return Result.create(StatusCode.ERROR,"参数为空,添加失败");
        }
        try {
            communityService.saveCommunity(name,description,type);
            return Result.create(StatusCode.OK,"添加成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"添加社团失败"+e.getMessage());
        }



    }

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteCommunity/{id}")
    public Result deleteCommunity(@PathVariable("id") Integer id){

        if(!formatUtil.checkPositive(id)){
            return Result.create(StatusCode.ERROR,"id值不合法，删除失败");
        }
        try{
            communityService.deleteCommunityById(id);
            return Result.create(StatusCode.OK,"删除成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"删除失败"+e.getMessage());
        }


    }

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/updateCommunity")
    public Result updateCommunity(@RequestParam(value = "name",required = false) String name,@RequestParam(value = "description",required = false) String description,
                                  @RequestParam(value = "type",required = false) String type,@RequestParam("id") Integer id){
        if(!formatUtil.checkObjectNull(id)){
            return Result.create(StatusCode.ERROR,"参数值不合法,修改失败");
        }
        try{
            communityService.updateCommunity(id,name,description,type);
            return Result.create(StatusCode.OK,"修改成功");
        }catch (RuntimeException e){
                return Result.create(StatusCode.ERROR,"修改失败"+e.getMessage());
        }

    }

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/findCommunity/{id}")
    public Result findCommunity(@PathVariable("id") Integer id){

        if(!formatUtil.checkPositive(id)){
            return Result.create(StatusCode.ERROR,"id值不合法,查找失败");
        }
        try{
            Community community = communityService.findCommunityById(id);
            return Result.create(StatusCode.OK,"操作成功",community);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"获取信息失败"+e.getMessage());
        }

    }

    @GetMapping("/searchCommunity")
    public Result searchCommunity(@RequestParam("fields") String fields){

        if(!formatUtil.checkStringNull(fields)){
            return Result.create(StatusCode.ERROR,"参数为空值");
        }
        try{
            List<Community> communities = communityService.searchCommunity(fields);
            return Result.create(StatusCode.OK,"查询成功",communities);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR, "获取信息失败" + e.getMessage());
        }
    }

    @GetMapping("/getUser")
    public Result getUser(){

        try{
            List<User> users = userService.getAllUser();
            return Result.create(StatusCode.OK,"查找成功",users);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"查找失败"+e.getMessage());
        }

    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){

        try{
            userService.updateUser(user);
            return Result.create(StatusCode.OK,"更新成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"更新失败"+e.getMessage());
        }

    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){

        try{
            userService.saveUser(user);
            return Result.create(StatusCode.OK,"保存成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"保存用户失败"+e.getMessage());
        }

    }

    @GetMapping("/searchUser")
    public Result searchUser(@RequestParam("fields") String fields){

        try{
            List<User> users = userService.searchUserByFields(fields);
            return Result.create(StatusCode.OK,"查找用户成功",users);
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"查找用户失败"+e.getMessage());
        }

    }

    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestParam("id") Integer id){

        try{
            userService.deleteUserById(id);
            return Result.create(StatusCode.OK,"删除用户成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"删除用户失败"+e.getMessage());
        }

    }





}
