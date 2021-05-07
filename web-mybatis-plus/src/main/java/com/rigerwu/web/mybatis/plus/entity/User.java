package com.rigerwu.web.mybatis.plus.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * created by riger on 2021/2/1
 */
@Data
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -1736466891297733266L;


    private Long id;

    private String name;

    private Integer age;

    private String email;

    /**
     * 逻辑删除字段
     */
    private String deleted;
}
