package com.baixiaowen.alllearning.controller;

import com.baixiaowen.alllearning.domain.common.PageQuery;
import com.baixiaowen.alllearning.domain.common.PageResult;
import com.baixiaowen.alllearning.domain.common.ResponseResult;
import com.baixiaowen.alllearning.domain.dto.UserDTO;
import com.baixiaowen.alllearning.domain.dto.UserQueryDTO;
import com.baixiaowen.alllearning.domain.vo.UserVO;
import com.baixiaowen.alllearning.exception.ErrorCodeEnum;
import com.baixiaowen.alllearning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult save(@RequestBody UserDTO userDTO) {
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
    public ResponseResult update(@PathVariable("id") Long id
            , @RequestBody UserDTO userDTO) {

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
    public ResponseResult delete(@PathVariable("id") Long id) {

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
     * @param pageNo
     * @param pageSize
     * @param query    用户查询DTO
     * @return
     */
    @GetMapping
    public ResponseResult<PageResult> query(Integer pageNo,
                                            Integer pageSize,
                                            UserQueryDTO query) {
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