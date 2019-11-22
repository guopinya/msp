package com.project.user.mapper;

import com.project.user.entity.UserAddr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 用户收货地址表 Mapper 接口
 *
 * @author zhuyifa
 * @since 2019-07-02
 */
@Repository
public interface UserAddrMapper extends BaseMapper<UserAddr> {

    /**
     * 通过用户ID和默认为true查找
     *
     * @param userId 用户ID
     * @return
     */
    List<UserAddr> selectByUserIdAndIsDefaultTrue(String userId, Boolean isDefault);
}
