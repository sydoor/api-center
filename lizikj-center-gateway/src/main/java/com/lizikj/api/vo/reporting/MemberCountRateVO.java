package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/9/13.
 */
@ApiModel
public class MemberCountRateVO {
    /**
     * 等级名称
     */
    @ApiModelProperty(value = "等级名称")
    private String levelName;
    /**
     * 等级会员数
     */
    @ApiModelProperty(value = "等级会员数")
    private Integer levelCount;
    /**
     * 等级占比
     */
    @ApiModelProperty(value = "等级占比")
    private Double levelRate;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(Integer levelCount) {
        this.levelCount = levelCount;
    }

    public Double getLevelRate() {
        return levelRate;
    }

    public void setLevelRate(Double levelRate) {
        this.levelRate = levelRate;
    }
}
