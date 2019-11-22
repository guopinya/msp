package com.project.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 角色表
 *
 * @author zhuyifa
 * @since 2019-08-05
 */
@TableName("system_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色备注
     */
    private String remark;

    /**
     * 持有菜单ID
     */
    private String holdMenuIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHoldMenuIds() {
        return holdMenuIds;
    }

    public void setHoldMenuIds(String holdMenuIds) {
        this.holdMenuIds = holdMenuIds;
    }
}
