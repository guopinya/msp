package com.project.msp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 店铺-gpy
 */
@Data
@TableName(value = "msp_shop")
public class Shop extends BaseEnity implements Serializable {
    @TableId
    private String id;
    private String areaId;
    private String parentId0;
    private String parentId1;
    private String parentId2;
    private String shopName;
    private String no;
    private String image;
    private String banner;
    private String tag;
    private String state;
    private String stateFlag;
    private String stime;
    private String etime;
    private String addr;
    private String phone;
    private String isHot;
    private String isRecommd;
    private String detail;
}