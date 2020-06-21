package com.baixiaowen.alllearning.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户VO实体
 */
@Data
public class UserVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1L;

    /**
     * 用户名
     */
    private String userName;

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

}