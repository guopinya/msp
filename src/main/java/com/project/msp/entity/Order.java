package com.project.msp.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.common.entity.BaseEnity;
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
    private String state;
    private String origin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date preTime;
    private Date payTime;
    private Date ServiceTime;
    private Date SuccTime;
    private Date EvaTime;
    private String remark;
    private BigDecimal baseAmount;
    private BigDecimal payAmount;
    private BigDecimal creditAmount;
    private String payType;
    private String projectId;
    private String orderProjectImage;
    private String orderProjectName;
    private String orderProjectTag;
    private String orderProjectPrice;
    private String areaId;
    private String shopId;
    private String orderShopName;
    private String orderShopAddr;
    private String orderShopPhone;
    private String servicerId;
    private String orderServicerName;
}
