package com.project.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.project.common.utils.HttpUtils;
import com.project.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 */
@Component
public class MetaHandler implements MetaObjectHandler {

    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        String userId = SecurityUtils.getUserId();
        String ipAddr = HttpUtils.getIpAddress();
        this.setFieldValByName("initTime", new Date(), metaObject);
        this.setFieldValByName("initAddr", ipAddr, metaObject);
        this.setFieldValByName("initUser", ipAddr, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateAddr", ipAddr, metaObject);
        this.setFieldValByName("updateUser", userId, metaObject);
    }

    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        String userId = SecurityUtils.getUserId();
        String ipAddr = HttpUtils.getIpAddress();
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateAddr", ipAddr, metaObject);
        this.setFieldValByName("updateUser", userId, metaObject);
    }

}