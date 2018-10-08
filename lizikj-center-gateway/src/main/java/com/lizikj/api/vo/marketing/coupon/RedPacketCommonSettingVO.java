package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.PlayCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/8/16 9:50
 */
@ApiModel(value = "红包通用设置")
public class RedPacketCommonSettingVO {
    @ApiModelProperty(value = "活动邀请码")
    private PlayCodeEnum playCodeEnum;
    @ApiModelProperty(value = "随机红包最低金额")
    private Long minRandomAmount;
    @ApiModelProperty(value = "随机红包最大金额")
    private Long maxRandomAmount;
    @ApiModelProperty(value = "固定额度")
    private Long fixedAmount;
    @ApiModelProperty(value = "有效小时数")
    private Integer validHour;
    @ApiModelProperty(value = "一天距离凌晨的秒数 开始秒数")
    private Long startSeconds;
    /**
     * 一天距离凌晨的秒数 结束秒数
     */
    @ApiModelProperty(value = "一天距离凌晨的秒数 结束秒数")
    private Long endSeconds;
    /**
     * 每日限额
     */
    @ApiModelProperty(value = "红包每日限额")
    private Long redPacketDailyMaxAmount;

    public PlayCodeEnum getPlayCodeEnum() {
        return playCodeEnum;
    }

    public void setPlayCodeEnum(PlayCodeEnum playCodeEnum) {
        this.playCodeEnum = playCodeEnum;
    }

    public Long getMinRandomAmount() {
        return minRandomAmount;
    }

    public void setMinRandomAmount(Long minRandomAmount) {
        this.minRandomAmount = minRandomAmount;
    }

    public Long getMaxRandomAmount() {
        return maxRandomAmount;
    }

    public void setMaxRandomAmount(Long maxRandomAmount) {
        this.maxRandomAmount = maxRandomAmount;
    }

    public Long getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(Long fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public Integer getValidHour() {
        return validHour;
    }

    public void setValidHour(Integer validHour) {
        this.validHour = validHour;
    }

    public Long getStartSeconds() {
        return startSeconds;
    }

    public void setStartSeconds(Long startSeconds) {
        this.startSeconds = startSeconds;
    }

    public Long getEndSeconds() {
        return endSeconds;
    }

    public void setEndSeconds(Long endSeconds) {
        this.endSeconds = endSeconds;
    }

    public Long getRedPacketDailyMaxAmount() {
        return redPacketDailyMaxAmount;
    }

    public void setRedPacketDailyMaxAmount(Long redPacketDailyMaxAmount) {
        this.redPacketDailyMaxAmount = redPacketDailyMaxAmount;
    }
}
