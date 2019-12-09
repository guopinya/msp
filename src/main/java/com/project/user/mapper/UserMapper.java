package com.project.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表 Mapper 接口
 *
 * @author zhuyifa
 * @since 2019-06-12
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询分页
     *
     * @param user 前端用户条件
     * @return 拍品分页
     */
    List<User> list(Wrapper<User> user);

    /**
     * 查询分页
     *
     * @param page 分页条件
     * @param user 前端用户条件
     * @return 拍品分页
     */
    IPage<User> pageByUserCond(IPage<User> page, Wrapper<User> user);

    /**
     * 根据用户名搜索用户
     *
     * @param
     * @return
     */
    List<User> selectUserByName(String name);

}