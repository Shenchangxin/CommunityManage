package com.shenchangxin.community.controller;

import com.shenchangxin.community.dto.ActivityRequestDTO;
import com.shenchangxin.community.entity.Result;
import com.shenchangxin.community.entity.StatusCode;
import com.shenchangxin.community.pojo.Activity;
import com.shenchangxin.community.pojo.Community;
import com.shenchangxin.community.service.ActivityService;
import com.shenchangxin.community.service.CommunityService;
import com.shenchangxin.community.utils.DateUtil;
import com.shenchangxin.community.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private FormatUtil formatUtil;
    @Autowired
    private DateUtil dateUtil;

    @GetMapping("/getActivity")
    public Result getActivity(){

        try{
            List<Activity> activities = activityService.getActivity();
            return Result.create(StatusCode.OK,"查询成功",activities);
        }catch (RuntimeException e){
            return Result.create(StatusCode.OK,"查询失败"+e.getMessage());
        }

    }

    @GetMapping("/activity/{id}")
    public Result getActivityById(@PathVariable("id") Integer id){
        try{

            Activity activity = activityService.findActivityById(id);
            return Result.create(StatusCode.OK,"查询成功",activity);
        }catch (RuntimeException e){
            return Result.create(StatusCode.OK,"查询失败"+e.getMessage());
        }
    };

    @PostMapping("/addActivity")
    public Result addActivity(@RequestBody ActivityRequestDTO requestDTO){

        if(!formatUtil.checkObjectNull(requestDTO)){
            return Result.create(StatusCode.ERROR,"参数值不合法");
        }
        try{
            Community community = communityService.findCommunityByName(requestDTO.getCommunityName());
            String CommunityId = community.getId().toString();
            Activity activity = new Activity();
            activity.setName(requestDTO.getName());
            activity.setContent(requestDTO.getContent());
            activity.setCommunityId(CommunityId);
            String CurrentDate=dateUtil.printDate();
            activity.setCreateTime(CurrentDate);
            activityService.addActivity(activity);
            return Result.create(StatusCode.OK,"保存成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"插入数据出错"+e.getMessage());
        }

    }

    @DeleteMapping("/deleteActivity/{id}")
    public Result deleteActivity(@PathVariable("id") Integer id){

        if (!formatUtil.checkPositive(id)){
            return Result.create(StatusCode.ERROR,"参数值不合法");
        }
        try{
            activityService.deleteActivityById(id);
            return Result.create(StatusCode.OK,"删除成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"删除数据出现异常"+e.getMessage());
        }
    }

    @PostMapping("/updateActivity")
    public Result updateActivity(@RequestParam(value = "id",required = false) Integer id,@RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "content",required = false) String content,@RequestParam(value = "communityName",required = false)String communityName){

        if (!formatUtil.checkPositive(id) ){
            return Result.create(StatusCode.ERROR,"参数值不合法");
        }
        try{
            activityService.updateActivity(id,name,content,communityName);
            return Result.create(StatusCode.OK,"更新数据成功");
        }catch (RuntimeException e){
            return Result.create(StatusCode.ERROR,"更新数据出错"+e.getMessage());
        }

    }



}
