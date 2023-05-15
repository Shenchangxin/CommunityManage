package com.shenchangxin.community.mapper;

import com.shenchangxin.community.pojo.Community;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommunityMapper {

    void addCommunity(Community community);

    void deleteCommunityById(Integer communityId);

    void updateCommunity(Integer id,String name,String description,String typeId);

    Community findCommunityById(Integer communityId);

    List<Community> getAllCommunity();

    List<Community> searchCommunity(@Param(value="fields") String fields);

    Community findCommunityByName(String name);


}
