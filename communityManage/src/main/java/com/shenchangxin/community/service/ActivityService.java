package com.shenchangxin.community.service;


import com.shenchangxin.community.pojo.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {

    List<Activity> getActivity();

    Activity findActivityById(Integer id);

    void addActivity(Activity activity);

    void deleteActivityById(Integer id);

    void updateActivity(Integer id,String name,String content,String communityName);
}
