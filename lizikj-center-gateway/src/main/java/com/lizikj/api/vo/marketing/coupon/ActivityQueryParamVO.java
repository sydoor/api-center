package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.ActivityStatusEnum;
import com.lizikj.marketing.api.enums.SourceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/7/31 17:35
 */
@ApiModel(value = "活动组合查询条件")
public class ActivityQueryParamVO {
    @ApiModelProperty(value = "活动ID")
    private Long activityId;
    @ApiModelProperty(value = "活动名称")
    private String activityName;
    @ApiModelProperty(value = "邀请码")
    private String playCode;
    @ApiModelProperty(value = "活动状态")
    private ActivityStatusEnum activityStatusEnum;
    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;
    @ApiModelProperty(value = "活动来源")
    private SourceEnum sourceEnum;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "结束时间")
    private Date startTime;
    @ApiModelProperty(value = "开始时间")
    private Date endTime;


    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public ActivityStatusEnum getActivityStatusEnum() {
        return activityStatusEnum;
    }

    public void setActivityStatusEnum(ActivityStatusEnum activityStatusEnum) {
        this.activityStatusEnum = activityStatusEnum;
    }

    public Long getTradeAreaId() {
        return tradeAreaId;
    }

    public void setTradeAreaId(Long tradeAreaId) {
        this.tradeAreaId = tradeAreaId;
    }

    public SourceEnum getSourceEnum() {
        return sourceEnum;
    }

    public void setSourceEnum(SourceEnum sourceEnum) {
        this.sourceEnum = sourceEnum;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
