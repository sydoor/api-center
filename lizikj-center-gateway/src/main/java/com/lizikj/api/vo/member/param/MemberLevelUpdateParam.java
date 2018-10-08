package com.lizikj.api.vo.member.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/8/28.
 */
public class MemberLevelUpdateParam {
    /**
     * 等级id
     */
    @ApiModelProperty(value = "等级id")
    private Long levelId;
    /**
     * 等级名
     */
    @ApiModelProperty(value = "等级名")
    private String levelName;

    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    private Double discount;

    /**
     * 是否开放积分功能 0关 1开
     */
    @ApiModelProperty(value = "是否开放积分功能 0关 1开")
    private Integer openCreditStatus;

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getOpenCreditStatus() {
        return openCreditStatus;
    }

    public void setOpenCreditStatus(Integer openCreditStatus) {
        this.openCreditStatus = openCreditStatus;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "MemberLevelUpdateParam{" +
                "levelId=" + levelId +
                ", levelName='" + levelName + '\'' +
                ", discount=" + discount +
                ", openCreditStatus=" + openCreditStatus +
                '}';
    }
}
