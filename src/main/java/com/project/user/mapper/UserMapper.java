package com.project.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
     * 根据字段搜索用户
     *
     * @param word 搜索字段
     * @return
     */
    //IPage<Posts> selectUserByWord(IPage page, String word);

    /**
     * 根据用户名搜索用户
     *
     * @param
     * @return
     */
    List<User> selectUserByName(String name);

}