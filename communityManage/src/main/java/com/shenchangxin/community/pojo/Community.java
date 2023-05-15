package com.shenchangxin.community.pojo;

import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
@ToString
public class Community {

    public Community(){

    }

    private Integer id;
    private String name;
    private String description;
    private String typeId;
    private String userId;
    private String createTime;
    private User user;
    private Type type;

}
