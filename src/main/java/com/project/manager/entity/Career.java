package com.project.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 职业
 *
 * @author tangmingxuan
 * @since 2019-07-16
 */
public class Career implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 职业ID
     */
    @TableId
    private String careerId;

    /**
     * 职业名称
     */
    private String careerName;

    /**
     * 职业认证等级
     */
    private Integer careerCertLevel;

    public Career(String careerId, String careerName) {
        this.careerId = careerId;
        this.careerName = careerName;
    }

    public String getCareerId() {
        return careerId;
    }

    public void setCareerId(String careerId) {
        this.careerId = careerId;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public Integer getCareerCertLevel() {
        return careerCertLevel;
    }

    public void setCareerCertLevel(Integer careerCertLevel) {
        this.careerCertLevel = careerCertLevel;
    }
}
