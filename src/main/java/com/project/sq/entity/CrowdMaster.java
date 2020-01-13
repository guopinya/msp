package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;

/**
 * 众筹达人
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_crowd_master")
public class CrowdMaster extends BaseEnity {

    /**
     * 众筹达人ID
     */
    @TableId
    private String id;
    /**
     * 众筹ID
     */
    private String crowdId;
    /**
     * 达人ID（用户ID）
     */
    private String masterId;
    /**
     * 收货姓名
     */
    private String fullName;
    /**
     * 收货手机号
     */
    private String cellphone;
    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 用户昵称
     */
    @TableField(exist = false)
    private String nickname;
    /**
     * 单份金额
     */
    @TableField(exist = false)
    private Integer singleAmount;

}
