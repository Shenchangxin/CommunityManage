package com.shenchangxin.community.mapper;

import com.shenchangxin.community.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {

    List<Activity> getAllActivity();

    Activity findActivityById(Integer id);

    void saveActivity(Activity activity);

    void deleteActivityById(Integer id);

    void updateActivity(Integer id, String name, String content, String communityId);
}
