package com.baixiaowen.alllearning.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体对象
 */
@Data
@TableName("user")
public class UserDO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1L;

    /** 用户主信息 */

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

    /** 系统主信息 */

    /**
     * 数据库主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 数据库创建时间
     */
    private LocalDateTime created;

    /**
     * 数据库修改时间
     */
    private LocalDateTime modified;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改者
     */
    private String operator;

    /**
     * 逻辑删除字段：0:正常, 1:逻辑删除
     */
    private Integer status;

    /**
     * 版本号  -- 数据的乐观锁
     */
    @Version
    private Long version;
}