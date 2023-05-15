package com.shenchangxin.community.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 返回结果实体类
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString
public class Result implements Serializable {

    private Integer code;// 返回码

    private String message;//返回信息

    private Object data;// 返回数据

    private Result() {
    }


    private Result(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    private Result(Integer code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result create(Integer code, String message) {
        return new Result(code, message);
    }

    public static Result create(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }





}
