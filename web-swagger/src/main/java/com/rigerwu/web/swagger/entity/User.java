package com.rigerwu.web.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * created by Riger on 2021/2/8
 */
@Data
@ApiModel("用户")
public class User {

    @ApiModelProperty(value = "用户id", required = true, example = "1")
    private Long id;
    @ApiModelProperty(value = "用户名", required = true, example = "Tom")
    private String username;
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    private String password;
    @ApiModelProperty(value = "年龄", required = true, example = "18")
    private Integer age;
    @ApiModelProperty(value = "地址", required = true, example = "ShangHai")
    private String addr;
    @ApiModelProperty(value = "性别 0 男 1 女", required = true, example = "0")
    private Integer gender;
}
