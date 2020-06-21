package com.baixiaowen.alllearning.domain.common;

import com.baixiaowen.alllearning.exception.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结果模型
 */
@Data
public class ResponseResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 编码
     */
    private String code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 结果
     */
    private T result;

    /**
     * 成功
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T result) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setSuccess(Boolean.TRUE);
        responseResult.setResult(result);

        return responseResult;
    }

    /**
     * 失败
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(String code, String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setSuccess(Boolean.FALSE);
        responseResult.setCode(code);
        responseResult.setMessage(message);

        return responseResult;
    }

    /**
     * 失败
     * @param codeEnum
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(ErrorCodeEnum codeEnum){
        return failure(codeEnum.getCode(), codeEnum.getMessage());
    }
}