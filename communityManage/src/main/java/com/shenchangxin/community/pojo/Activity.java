package com.shenchangxin.community.pojo;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Activity {

    private Integer id;
    private String name;
    private String content;
    private Integer views;
    private String createTime;
    private String communityId;
    private String userId;
    private User user;
    private Community community;
}
