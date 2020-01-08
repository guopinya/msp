package com.project.msp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import com.project.user.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 搜索-gpy
 */
@Data
@TableName(value = "msp_search")
public class Search extends BaseEnity implements Serializable {
    @TableId
    private String id;
    private String areaId;
    private String searchValue;
    private String searchType;
    private String searchVersion;
    private String searchModel;

    @TableField(exist = false)
    private User user;

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue.trim();
    }

}
