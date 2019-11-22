package com.project.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.user.entity.UserCareer;

import org.springframework.stereotype.Repository;

/**
 * 用户职业表 Mapper 接口
 *
 * @author tangmingxuan
 * @since 2019-08-02
 */
@Repository
public interface UserCareerMapper extends BaseMapper<UserCareer> {


    /**
     *获取用户职业
     *
     * @param userId 用户ID
     * @return
     */
    //UserCareer selectUserCareerByUserId(String userId);
}