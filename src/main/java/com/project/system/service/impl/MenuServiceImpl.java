package com.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.system.entity.Menu;
import com.project.system.entity.Role;
import com.project.system.mapper.MenuMapper;
import com.project.system.mapper.RoleMapper;
import com.project.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单服务实现
 *
 * @author zhuyifa
 * @since 2019-07-25
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 通过用户ID查询列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<Menu> listByUserId(String userId) {
        String holdMenuIds = null;

        Role role = roleMapper.selectByUserId(userId);
        if (role != null) {
            holdMenuIds = role.getHoldMenuIds();
        }

        List<Menu> menuList = selectListByParentIdAndHoldMenuIds(Menu.DEFAULT_PARENT, holdMenuIds);
        for (Menu menu : menuList) {

            if (!menu.getLastLevel()) {

                List<Menu> menuList2 = selectListByParentIdAndHoldMenuIds(menu.getId(), holdMenuIds);
                if (holdMenuIds != null) {
                    menu.setChildren(menuList2);
                    continue;
                }

                for (Menu menu2 : menuList2) {

                    if (!menu2.getLastLevel()) {

                        List<Menu> menuList3 = selectListByParentIdAndHoldMenuIds(menu2.getId(), holdMenuIds);
                        menu2.setChildren(menuList3);
                    }
                }

                menu.setChildren(menuList2);
            }

        }
        return menuList;
    }

    /**
     * 通过父菜单ID和持有菜单ID查询列表
     *
     * @param parentId    父菜单ID
     * @param holdMenuIds 持有菜单ID
     * @return 菜单列表
     */
    private List<Menu> selectListByParentIdAndHoldMenuIds(String parentId, String holdMenuIds) {
        Wrapper<Menu> wrapper = new QueryWrapper<Menu>().lambda()
                // 父分类ID
                .eq(Menu::getParentId, parentId)
                // 排序
                .orderByAsc(Menu::getSortNumber);
        List<Menu> menuList = menuMapper.selectList(wrapper);

        if (holdMenuIds == null) {
            return menuList;
        }

        List<Menu> filterList = new ArrayList<>();

        for (Menu menu : menuList) {
            if (holdMenuIds.contains(menu.getId())) {
                filterList.add(menu);
            }
        }
        return filterList;
    }

    /**
     * 保存菜单
     *
     * @param entity 菜单实体
     * @return 操作结果
     */
    @Override
    public boolean save(Menu entity) {
        boolean save = super.save(entity);

        String parentId = entity.getParentId();
        if (!Menu.DEFAULT_PARENT.equals(parentId)) {
            Menu menu = new Menu();
            menu.setId(parentId);
            menu.setLastLevel(false);
            menuMapper.updateById(menu);
        }
        return save;
    }
}
