package com.shenchangxin.community.service.impl;

import com.shenchangxin.community.mapper.CommunityMapper;
import com.shenchangxin.community.mapper.TypeMapper;
import com.shenchangxin.community.mapper.UserMapper;
import com.shenchangxin.community.pojo.Community;
import com.shenchangxin.community.pojo.Type;
import com.shenchangxin.community.pojo.User;
import com.shenchangxin.community.service.CommunityService;
import com.shenchangxin.community.utils.JsonWebTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JsonWebTokenUtil jsonWebTokenUtil;

    @Autowired
    private HttpServletRequest request;

    /**
     * 根据id删除社团
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCommunityById(Integer id) {
        communityMapper.deleteCommunityById(id);
    }

    /**
     * 根据id查找社团
     * @param id
     * @return
     */
    @Override
    public Community findCommunityById(Integer id) {
        return communityMapper.findCommunityById(id);
    }

    /**
     * 保存社团
     *
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCommunity(String name,String description,String typeName) {
        User user = userMapper.findUserByUsername(jsonWebTokenUtil.getUsernameFromRequest(request));
        Type type = typeMapper.findTypeByName(typeName);
        String typeId = type.getId().toString();
        Community community = new Community();
        community.setTypeId(typeId);
        community.setName(name);
        community.setUserId(user.getId().toString());
        community.setDescription(description);
        communityMapper.addCommunity(community);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCommunity(Integer id,String name,String description,String type) {
        String typeId = null;
        if(type !=null ){
            Type type1 = typeMapper.findTypeByName(type);
            typeId = type1.getId().toString();
        }
        communityMapper.updateCommunity(id,name,description,typeId);
    }

    @Override
    public List<Community> getAllCommunity() {

        return communityMapper.getAllCommunity();
    }

    @Override
    public List<Community> searchCommunity(String fields) {
        return communityMapper.searchCommunity(fields);
    }

    @Override
    public Community findCommunityByName(String name) {
        return communityMapper.findCommunityByName(name);
    }
}
