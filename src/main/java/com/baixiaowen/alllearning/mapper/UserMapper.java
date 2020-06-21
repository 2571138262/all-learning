package com.baixiaowen.alllearning.mapper;

import com.baixiaowen.alllearning.domain.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    // 需要自定义一些方法

}