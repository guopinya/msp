package com.project.stat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.stat.entity.DailyUser;
import com.project.stat.mapper.DailyUserMapper;
import com.project.stat.service.IDailyUserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 部落类
 *
 * @author tangmingxuan
 * @since 2019-09-05
 */
@Service
public class DailyUserServiceImpl extends ServiceImpl<DailyUserMapper, DailyUser> implements IDailyUserService {

    @Autowired
    private DailyUserMapper dailyUserMapper;


    /**
     * 统计用户登录 服务类
     *
     * @param
     * @param
     * @return
     */
    @Override
    public void addLoginDailyUserRecord(String userId) {
        if (!dailyUserMapper.existsLoginByUserIdAndTime(userId, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
            DailyUser dailyUser = new DailyUser(userId, DailyUser.STAT_DAILY_LOGIN);
            dailyUser.setStatTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            dailyUserMapper.insert(dailyUser);
        }

    }

    /**
     * 统计用户登录 服务类
     *
     * @param
     * @param
     * @return
     */
    @Override
    public void addOnLineUserRecord(String userId) {
        if (!dailyUserMapper.existsOnLineByUserIdAndTime(userId, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH")))) {
            DailyUser dailyUser = new DailyUser(userId, DailyUser.STAT_DAILY_ONLINE);
            dailyUser.setStatTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH")));
            dailyUserMapper.insert(dailyUser);
        }

    }
}