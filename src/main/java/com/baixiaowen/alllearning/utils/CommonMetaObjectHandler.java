package com.baixiaowen.alllearning.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 公共元数据处理器
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新建的时候自动填充的字段
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("新建时，开始填充系统字段");

        // 自动赋值创建时间
        this.strictInsertFill(metaObject,
                "created",
                LocalDateTime.class,
                LocalDateTime.now());
        // 自动赋值修改时间
        this.strictInsertFill(metaObject,
                "modified",
                LocalDateTime.class,
                LocalDateTime.now());
        // 自动赋值创建人
        this.strictInsertFill(metaObject,
                "creator",
                String.class,
                "TODO 从上下文中获取当前创建人信息");
        // 自动赋值修改人
        this.strictInsertFill(metaObject,
                "operator",
                String.class,
                "TODO 从上下文中获取当前修改人信息");
        // 状态字段
        this.strictInsertFill(metaObject,
                "status",
                Integer.class,
                0);
        // 版本号
        this.strictInsertFill(metaObject,
                "version",
                Long.class,
                1L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新时，开始填充系统字段");

        // 更新时间
        this.strictUpdateFill(metaObject,
                "modified",
                LocalDateTime.class,
                LocalDateTime.now());
        // 更新修改人
        this.strictUpdateFill(metaObject,
                "operator",
                String.class,
                "TODO 从上下文中获取当前修改人信息");
    }
}