package com.project.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 用户职业表
 *
 * @author tangmingxuan
 * @since 2019-08-02
 */
public class UserCareer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 第一职业ID
     */
    private String careerFirstId;

    /**
     * 第二职业ID
     */
    private String careerSecondId;

    /**
     * 第三职业ID
     */
    private String careerThirdId;

    /**
     * 第一职业名称
     */
    @TableField(exist = false)
    private String careerFirstName;

    /**
     * 第二职业名称
     */
    @TableField(exist = false)
    private String careerSecondName;

    /**
     * 第三职业名称
     */
    @TableField(exist = false)
    private String careerThirdName;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCareerFirstId() {
        return careerFirstId;
    }

    public void setCareerFirstId(String careerFirstId) {
        this.careerFirstId = careerFirstId;
    }

    public String getCareerSecondId() {
        return careerSecondId;
    }

    public void setCareerSecondId(String careerSecondId) {
        this.careerSecondId = careerSecondId;
    }

    public String getCareerThirdId() {
        return careerThirdId;
    }

    public void setCareerThirdId(String careerThirdId) {
        this.careerThirdId = careerThirdId;
    }

    public String getCareerFirstName() {
        return careerFirstName;
    }

    public void setCareerFirstName(String careerFirstName) {
        this.careerFirstName = careerFirstName;
    }

    public String getCareerSecondName() {
        return careerSecondName;
    }

    public void setCareerSecondName(String careerSecondName) {
        this.careerSecondName = careerSecondName;
    }

    public String getCareerThirdName() {
        return careerThirdName;
    }

    public void setCareerThirdName(String careerThirdName) {
        this.careerThirdName = careerThirdName;
    }


}