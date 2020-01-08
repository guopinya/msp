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
 * 评价-gpy
 */
@Data
@TableName(value = "msp_evaluate", resultMap = "evaluateResultMap")
public class Evaluate extends BaseEnity implements Serializable {
    @TableId
    private String id;
    private String userId;
    private String orderId;
    private String projectId;
    private String shopId;
    private String servicerId;

    private String evaluateScore;
    private String evaluateDetail;
    private String evaluateBanner;
    private String evaluateState;

    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Order order;
    @TableField(exist = false)
    private Project project;
    @TableField(exist = false)
    private Shop shop;
    @TableField(exist = false)
    private SysUser servicer;
}
