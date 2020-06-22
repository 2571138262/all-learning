package com.baixiaowen.alllearning.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "用户姓名不能为空！")
    private String username;

}