package com.baixiaowen.alllearning.controller;

import com.baixiaowen.alllearning.domain.common.PageResult;
import com.baixiaowen.alllearning.domain.common.ResponseResult;
import com.baixiaowen.alllearning.domain.dto.UserDTO;
import com.baixiaowen.alllearning.domain.dto.UserQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    /**
     * 保存用户信息
     * POST /api/uesrs UserDTO userDTO
     *
     * @RequestBody 从Request 的 Body中获取数据
     */
    @PostMapping
    public ResponseResult save(@RequestBody UserDTO userDTO) {
        return ResponseResult.success("新增成功!");
    }

    /**
     * 更新用户信息
     * PUT /api/users/{id} UserDTO userDTO
     *
     * @param id
     * @param userDTO
     * @return
     */
    @PutMapping
    public ResponseResult update(@PathVariable Long id
            , @RequestBody UserDTO userDTO) {
        return ResponseResult.success("更新成功");
    }

    /**
     * 删除用户信息
     * DELETE /api/users/{id}
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public ResponseResult delete(@PathVariable Long id) {
        return ResponseResult.success("删除成功");
    }

    /**
     * 查询用户信息
     * GET /api/users/
     *
     * @param pageNo
     * @param pageSize
     * @param query 用户查询DTO
     * @return
     */
    @GetMapping
    public ResponseResult<PageResult> query(Integer pageNo,
                            Integer pageSize,
                            @RequestBody UserQueryDTO query){
        return ResponseResult.success(new PageResult());
    }

}