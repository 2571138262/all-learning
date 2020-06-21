package com.baixiaowen.alllearning.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页查询对象
 */
@Data
public class PageQuery<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1L;

    /**
     * 当亲页
     */
    private Integer pageNo = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 20;

    /**
     * 动态查询条件
     */
    private T query;

}