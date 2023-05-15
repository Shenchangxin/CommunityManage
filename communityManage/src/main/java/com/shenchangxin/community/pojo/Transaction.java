package com.shenchangxin.community.pojo;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Transaction {


    private Integer id;
    private String activityId;
    private String communityId;
    private String remark;
    private double money;
    private String type;
    private Activity activity;
    private Community community;


}
