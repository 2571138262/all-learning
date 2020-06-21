package com.baixiaowen.alllearning.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页查询返回实体
 */
@Data
public class PageResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1L;

    /**
     * 当前页号
     */
    private Integer pageNo;

    /**
     * 每页行数
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private long pageNum;

    /**
     * 动态的内容
     */
    private T data;

}