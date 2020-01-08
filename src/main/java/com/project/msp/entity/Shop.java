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
    private String shopParentId0; // 区代
    private String shopParentId1; // 督导
    private String shopParentId2; //加盟商
    private String shopParentId3; //店长
    private String shopName;
    private String shopNo;
    private String shopImage;
    private String shopBanner;
    private String shopTag;
    private String shopState;
    private String shopStateFlag;
    private String shopStime;
    private String shopEtime;
    private String shopAddr;
    private String shopPhone;
    private String shopIsHot;
    private String shopIsRecommend;
    private String shopDetail;
}
