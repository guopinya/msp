package com.project.user.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.user.entity.User;

import java.util.List;

/**
 * 用户表 服务类
 *
 * @author zhuyifa
 * @since 2019-06-12
 */
public interface IUserService extends IService<User> {


    /**
     * 通过手机号获取
     *
     * @param mobile 手机号
     * @return 用户
     */
    User getByMobile(String mobile);

    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    List<User> list(Wrapper<User> user);

    /**
     * 按用户条件分页
     *
     * @param page 分页条件
     * @param user 用户条件
     * @return 分页对象
     */
    IPage<User> pageByUserCond(IPage<User> page, User user);

    /**
     * 通过用户ID获取
     *
     * @param userId 用户ID
     * @return 用户
     */
    User getByUserId(String userId);

    /**
     * 注册用户
     *
     * @param user     用户
     * @param password 密码
     * @return 用户
     */
    User registerUser(User user, String password);

    /**
     * 通过用户ID获取密码
     *
     * @param userId 用户ID
     * @return 密码
     */
    String getPasswordByUserId(String userId);

    /**
     * 修改密码
     *
     * @param userId   用户ID
     * @param password 密码
     */
    void modifyPassword(String userId, String password);

    /**
     * 判断能否发布
     *
     * @return 能否发布
     */
    Boolean isPutAllow();

    /**
     * 保存发布时间
     */
    void savePutTime();

    /**
     * 是否有重复名称
     *
     * @return
     */
    Boolean isDuplicateName(String userId, String name);
}
