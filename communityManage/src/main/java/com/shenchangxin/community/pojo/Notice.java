package com.shenchangxin.community.pojo;


import lombok.Data;
import lombok.ToString;
import java.util.Date;

@ToString
@Data
public class Notice {

    private Integer id;
    private String content;
    private String title;
    private String userId;
    private String communityId;
    private String createTime;
    private User user;
    private Community community;

}
