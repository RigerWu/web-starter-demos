package com.rigerwu.cache.redis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName user
 */
@Data
public class User implements Serializable{
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

}