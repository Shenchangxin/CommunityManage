package com.shenchangxin.community.service;

import com.shenchangxin.community.pojo.Community;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommunityService {

    void deleteCommunityById(Integer id);

    Community findCommunityById(Integer id);

    void saveCommunity(String name,String description,String type);

    void updateCommunity(Integer id,String name,String description,String type);

    List<Community> getAllCommunity();

    List<Community> searchCommunity(String fields);

    Community findCommunityByName(String name);
}
