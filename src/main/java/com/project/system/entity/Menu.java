package com.project.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 *
 * @author zhuyifa
 * @since 2019-07-25
 */
@TableName("system_menu")
public class Menu implements Serializable {

    public static final String DEFAULT_PARENT = "0";
    public static final String TYPE_MENU = "0";
    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID
     */
    @TableId
    private String id;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 路由地址
     */
    private String jump;

    /**
     * 排序数字
     */
    private Integer sortNumber;

    /**
     * 是否最后一级
     */
    private Boolean isLastLevel;

    /**
     * 子菜单列表
     */
    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Boolean getLastLevel() {
        return isLastLevel;
    }

    public void setLastLevel(Boolean lastLevel) {
        isLastLevel = lastLevel;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
