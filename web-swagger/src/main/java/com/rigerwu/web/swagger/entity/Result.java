package com.rigerwu.web.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * created by riger on 2021/2/5
 */
@Data
@ApiModel("api 通用返回结果")
public class Result<R> {

    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("返回码")
    private String code;
    @ApiModelProperty("返回码解释")
    private String msg;
    @ApiModelProperty("返回数据")
    private R data;


    public static <R> Result<R> ofSuccess(R data) {
        Result<R> result =  new Result<R>();
            result.setSuccess(true);
            result.setCode("00000");
            result.setMsg("success");
            result.setData(data);
            return result;
    }


    public static <R> Result<R> ofSuccessMsg(String msg) {
        Result<R> result =  new Result<R>();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }


    public static <R> Result<R> ofFail(String code, String msg) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


    public static <R> Result<R> ofThrowable(String code, Throwable throwable) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return result;
    }

}