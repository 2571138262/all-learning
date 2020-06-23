package com.baixiaowen.alllearning.controller;

import com.baixiaowen.alllearning.domain.common.PageQuery;
import com.baixiaowen.alllearning.domain.common.PageResult;
import com.baixiaowen.alllearning.domain.common.ResponseResult;
import com.baixiaowen.alllearning.domain.dto.UserDTO;
import com.baixiaowen.alllearning.domain.dto.UserQueryDTO;
import com.baixiaowen.alllearning.domain.vo.UserVO;
import com.baixiaowen.alllearning.exception.ErrorCodeEnum;
import com.baixiaowen.alllearning.service.UserService;
import com.baixiaowen.alllearning.utils.InsertValidationGroup;
import com.baixiaowen.alllearning.utils.UpdateValidationGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
// 开启简单注解对基础类型的校验
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 保存用户信息
     * POST /api/uesrs UserDTO userDTO
     *
     * @RequestBody 从Request 的 Body中获取数据
     */
    @PostMapping
    // 更新的时候，清空 缓存key 下所对应的数据
    @CacheEvict(cacheNames = "users-cache", allEntries = true)
    public ResponseResult save(
            @Validated(InsertValidationGroup.class)
            @RequestBody
                    UserDTO userDTO) {
        int save = userService.save(userDTO);
        if (save == 1) {
            return ResponseResult.success("新增成功!");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    /**
     * 更新用户信息
     * PUT /api/users/{id} UserDTO userDTO
     *
     * @param id
     * @param userDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseResult update(
            @NotNull
            @PathVariable("id")
                    Long id,
            @Validated(UpdateValidationGroup.class)
            @RequestBody
                    UserDTO userDTO) {

        int update = userService.update(id, userDTO);

        if (update == 1) {
            return ResponseResult.success("更新成功");
        }

        return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
    }

    /**
     * 删除用户信息
     * DELETE /api/users/{id}
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(
            @NotNull(message = "用户ID不能为空！")
            @PathVariable("id")
                    Long id) {

        int delete = userService.delete(id);

        if (delete == 1) {
            return ResponseResult.success("删除成功");
        }

        return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
    }

    /**
     * 查询用户信息
     * GET /api/users/
     *
     * @Cacheable(cacheNames = "users-cache")
     *  cacheNames : 就是要把缓存数据放到哪个key下作为一条数据进行缓存，
     *               如果不指定，将会使用所有的参数进行一个hash，得到的hash值就是缓存的key
     *
     * @param pageNo
     * @param pageSize
     * @param query    用户查询DTO
     * @return
     */
    @Cacheable(cacheNames = "users-cache")
    @GetMapping
    public ResponseResult<PageResult> query(@NotNull Integer pageNo,
                                            @NotNull Integer pageSize,
                                            @Validated UserQueryDTO query) {
        log.info("未使用缓存！");

        // 构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(query);

        // 查询
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);

        // 实体转换
        List<UserVO> userVOList = Optional.ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(userDTO, userVO);
                    // 对特殊字段做处理
                    userVO.setPassword("******");
                    if (!StringUtils.isEmpty(userDTO.getPhone())){
                        userVO.setPhone(userDTO.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})",
                                "$1****$2"));
                    }
                    return userVO;
                }).collect(Collectors.toList());

        // 封装返回结果
        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult, result);
        result.setData(userVOList);

        return ResponseResult.success(result);
    }

}