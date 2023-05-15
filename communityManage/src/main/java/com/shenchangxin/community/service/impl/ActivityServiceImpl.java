package com.shenchangxin.community.service.impl;

import com.shenchangxin.community.mapper.ActivityMapper;
import com.shenchangxin.community.pojo.Activity;
import com.shenchangxin.community.pojo.Community;
import com.shenchangxin.community.service.ActivityService;
import com.shenchangxin.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private  ActivityMapper activityMapper;


    @Autowired
    private CommunityService communityService;

    public ActivityServiceImpl(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }


    @Override
    public List<Activity> getActivity() {
        return activityMapper.getAllActivity();
    }

    @Override
    public Activity findActivityById(Integer id) {
        return activityMapper.findActivityById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addActivity(Activity activity) {
        activityMapper.saveActivity(activity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteActivityById(Integer id) {
        activityMapper.deleteActivityById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateActivity(Integer id, String name, String content, String communityName) {
        Community community = communityService.findCommunityByName(communityName);
        String communityId = community.getId().toString();
        activityMapper.updateActivity(id,name,content,communityId);
    }


}
