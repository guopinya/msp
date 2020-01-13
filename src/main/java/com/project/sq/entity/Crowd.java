package com.project.sq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.common.entity.BaseEnity;
import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

/**
 * 众筹
 *
 * @author zhuyifa
 */
@Data
@TableName(value = "sq_crowd")
public class Crowd extends BaseEnity {

    static final Integer DEPOSIT = 999;

    /**
     * 众筹ID
     */
    @TableId
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 众筹标题
     */
    private String title;
    /**
     * 众筹图片
     */
    private String image;
    /**
     * 众筹内容
     */
    private String content;
    /**
     * 开始时间（13位时间戳）
     */
    private String startTime;
    /**
     * 关闭时间（13位时间戳）
     */
    private String closeTime;
    /**
     * 已参与人数
     */
    private Integer joinedNum;
    /**
     * 预设参与人数
     */
    private Integer presetNum;
    /**
     * 众筹金额
     */
    private Integer totalAmount;
    /**
     * 单份金额
     */
    private Integer singleAmount;
    /**
     * 是否轮播
     */
    private Boolean isCarousel;
    /**
     * 是否推荐
     */
    private Boolean isRecommend;

    /**
     * 保证金
     */
    @TableField(exist = false)
    private String deposit = String.valueOf(DEPOSIT);
    /**
     * 当前时间
     */
    @TableField(exist = false)
    private String currentTime = String.valueOf(System.currentTimeMillis());
    /**
     * 是否参与
     */
    @TableField(exist = false)
    private Boolean isJoined;

    /**
     * 设置默认值
     */
    public void setDefaultValue() {
        // 已参与人数
        this.joinedNum = 0;
        // 单份金额
        if (this.presetNum != null && this.totalAmount != null) {
            this.singleAmount = this.totalAmount / this.presetNum;
        }
        // 是否轮播
        if (this.isCarousel == null) {
            this.isCarousel = Boolean.FALSE;
        }
        // 是否推荐
        if (this.isRecommend == null) {
            this.isRecommend = Boolean.FALSE;
        }
        // 显示顺序
        setSortNumber(RandomUtils.nextInt());
    }

    /**
     * 在众筹中
     *
     * @return 是否在众筹中
     */
    public boolean inCrowdfunding() {
        // 当前时间
        long currentTime = System.currentTimeMillis();
        // 开始时间
        long startTime = Long.parseLong(this.startTime);
        // 关闭时间
        long closeTime = Long.parseLong(this.closeTime);

        return joinedNum < presetNum
                && startTime < currentTime
                && currentTime < closeTime;
    }

    /**
     * 添加已参与人数
     *
     * @param quantity 数量
     */
    public void addJoinedNum(Integer quantity) {
        this.joinedNum += quantity;
    }

}
