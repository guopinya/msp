package com.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.system.entity.Menu;

import java.util.List;

/**
 * 菜单服务
 *
 * @author zhuyifa
 * @since 2019-07-25
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 通过用户ID查询列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<Menu> listByUserId(String userId);
}
