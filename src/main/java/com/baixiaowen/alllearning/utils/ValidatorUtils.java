package com.baixiaowen.alllearning.utils;

import com.baixiaowen.alllearning.exception.ErrorCodeEnum;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * 校验工具类  ---   手动校验的功能
 */
public class ValidatorUtils {

    /**
     * 校验器
     */
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 参数校验
     * @param o
     * @param groups
     * @param <T>
     */
    public static <T> void validate(T o, Class... groups){
        Set<ConstraintViolation<T>> validate = validator.validate(o, groups);

        // 如果校验结果不为空
        if (!CollectionUtils.isEmpty(validate)){
            StringBuffer exceptionMessage = new StringBuffer();
            validate.forEach(constraintViolation -> {
                exceptionMessage.append(constraintViolation.getMessage());
            });

            throw new RuntimeException(exceptionMessage.toString());
        }
    }

}