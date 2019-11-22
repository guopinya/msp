package com.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.system.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * 角色映射器
 *
 * @author zhuyifa
 * @date 2019-10-18
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户ID查询
     *
     * @param userId 用户ID
     * @return 角色
     */
    Role selectByUserId(String userId);
}