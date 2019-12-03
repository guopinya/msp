package com.project.msp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private String areaId;
    private String orderNo;
    private String state;
    private String origin;
    private Date preTime;
    private Date payTime;
    private Date ServiceTime;
    private Date SuccTime;
    private Date EvaTime;
    private BigDecimal baseAmount;
    private BigDecimal payAmount;
    private BigDecimal creditAmount;
    private String payType;
    private String projectId;
    private String shopId;
    private String servicerId;
    private String shopName;
    private String shopAddr;
    private String shopPhone;
    private String servicerName;
}
