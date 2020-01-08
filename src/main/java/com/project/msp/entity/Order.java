package com.project.msp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.common.entity.BaseEnity;
import com.project.system.entity.SysUser;
import com.project.user.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单-gpy
 */
@Data
@TableName(value = "msp_order")
public class Order extends BaseEnity implements Serializable {
    @TableId
    private String id;
    private String orderNo;
    private String userId;
    private String projectId;
    private String areaId;
    private String shopId;
    private String servicerId;

    private String orderState;
    private String orderOrigin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderPreTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderPayTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderServiceTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderSuccTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderEvaTime;
    private String orderRemark;
    private BigDecimal orderBaseAmount;
    private BigDecimal orderPayAmount;
    private BigDecimal orderCreditAmount;
    private String orderPayType;

    private String orderProjectImage;
    private String orderProjectName;
    private String orderProjectTag;
    private BigDecimal orderProjectPrice;
    private String orderShopName;
    private String orderShopAddr;
    private String orderShopPhone;
    private String orderServicerName;

    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Project project;
    @TableField(exist = false)
    private Shop shop;
    @TableField(exist = false)
    private SysUser servicer;
    @TableField(exist = false)
    private String username;
}
