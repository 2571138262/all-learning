package com.baixiaowen.alllearning.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据查询DTO实体
 */
@Data
public class UserQueryDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1L;

    /**
     * 用户名
     */
    private String username;

}