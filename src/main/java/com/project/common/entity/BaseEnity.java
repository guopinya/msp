package com.project.common.entity;

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

    private Date initTime;
    private String initAddr;
    private String initUser;
    private Date updateTime;
    private String updateAddr;
    private String updateUser;
}
