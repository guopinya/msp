package com.project.stat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.stat.entity.DailyUser;
import org.springframework.stereotype.Repository;

/**
 * Mapper 接口
 *
 * @author tangmingxuan
 * @since 2019-09-28
 */
@Repository
public interface DailyUserMapper extends BaseMapper<DailyUser> {

    /**
     * 通过用户ID和时间判断存在
     *
     * @param userId 用户ID
     * @param
     * @return
     */
    boolean existsLoginByUserIdAndTime(String userId, String statTime);

    /**
     * 通过用户ID和时间判断存在
     *
     * @param userId 用户ID
     * @param
     * @return
     */
    boolean existsOnLineByUserIdAndTime(String userId, String statTime);
}