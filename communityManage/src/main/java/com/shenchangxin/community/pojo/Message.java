package com.shenchangxin.community.pojo;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Message {

    private Integer id;
    private String commentUserId;
    private String commentContent;
    private String replyContent;
    private String commentTime;
    private String replyTime;
    private String replyUserId;
    private User user;
    private User replyUser;

}
