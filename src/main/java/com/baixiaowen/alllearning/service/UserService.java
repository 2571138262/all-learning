package com.baixiaowen.alllearning.service;

import com.baixiaowen.alllearning.domain.common.PageQuery;
import com.baixiaowen.alllearning.domain.common.PageResult;
import com.baixiaowen.alllearning.domain.dto.UserDTO;
import com.baixiaowen.alllearning.domain.dto.UserQueryDTO;

import java.util.List;

/**
 * 用户接口服务类
 */
public interface UserService {

    /**
     * 新增
     * @param userDTO
     * @return
     */
    int save(UserDTO userDTO);

    /**
     * 更新
     * @param id
     * @param userDTO
     * @return
     */
    int update(Long id, UserDTO userDTO);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);

}
