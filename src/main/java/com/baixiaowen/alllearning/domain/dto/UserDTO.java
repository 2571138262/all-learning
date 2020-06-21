package com.baixiaowen.alllearning.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户DTO实体
 */
@Data
public class UserDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 版本号  -- 数据的乐观锁
     */
    private Long version;
}