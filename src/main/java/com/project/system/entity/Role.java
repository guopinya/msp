package com.project.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表
 *
 * @author zhuyifa
 * @since 2019-08-05
 */
@Data
@TableName("system_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 区代理
     */
    public static final String DEFAULT_ID_DISTRICT = "1187637908193771521";
    /**
     * 督导
     */
    public static final String DEFAULT_ID_AGENT = "1187637908193771522";
    /**
     * 加盟商
     */
    public static final String DEFAULT_ID_SUPERVISION = "1187637908193771523";
    /**
     * 店长
     */
    public static final String DEFAULT_ID_FRANCHISEE = "1187637908193771524";

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
    private String isSet;
}
