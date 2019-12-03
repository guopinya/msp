package com.project.msp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
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
    private String name;
    private String type;
}
