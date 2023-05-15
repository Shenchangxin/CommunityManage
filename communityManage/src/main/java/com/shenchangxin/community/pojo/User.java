package com.shenchangxin.community.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class User {


    private Integer id;
    private String username;
    private String password;
    private String department;
    private String name;
    private String phone;
    private String image;
    private String grade;
    private String sex;
    private String period;
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private List<Role> roles;//角色列表

}
