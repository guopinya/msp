package com.project.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息表
 *
 * @author zhuyifa
 * @since 2019-06-28
 */
@Data
public class BaseEnity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sortNumber;

    private String isDisplay;
    @TableField(value = "init_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date initTime;
    @TableField(value = "init_addr", fill = FieldFill.INSERT)
    private String initAddr;
    @TableField(value = "init_user", fill = FieldFill.INSERT)
    private String initUser;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    @TableField(value = "update_addr", fill = FieldFill.INSERT_UPDATE)
    private String updateAddr;
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private String updateUser;
}
