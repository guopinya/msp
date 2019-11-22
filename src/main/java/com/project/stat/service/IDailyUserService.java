package com.project.stat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.stat.entity.DailyUser;


/**
 * 统计用户登录 服务类
 *
 * @author tangmingxuan
 * @since 2019-09-15
 */
public interface IDailyUserService extends IService<DailyUser> {


    /**
     * 增加每日登陆记录
     *
     * @param
     * @param
     * @return
     */
    void addLoginDailyUserRecord(String userId);

    /**
     * 增加在线人数纪录
     *
     * @param
     * @param
     * @return
     */
    void addOnLineUserRecord(String userId);
}