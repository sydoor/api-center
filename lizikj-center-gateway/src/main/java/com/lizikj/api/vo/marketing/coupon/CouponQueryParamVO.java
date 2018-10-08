package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.CardTypeEnum;
import com.lizikj.marketing.api.enums.CouponStatusEnum;
import com.lizikj.marketing.api.enums.SourceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/7/31 16:41
 */
@ApiModel(value = "卡券组合条件查询")
public class CouponQueryParamVO {
    @ApiModelProperty(value = "卡券ID")
    private String cardId;
    @ApiModelProperty(value = "活动ID")
    private Long activityId;
    @ApiModelProperty(value = "卡券类型")
    private CardTypeEnum cardTypeEnum;
    @ApiModelProperty(value = "卡券来源")
    private SourceEnum sourceEnum;
    @ApiModelProperty(value = "卡券名称")
    private String couponName;
    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "卡券状态")
    private CouponStatusEnum couponStatusEnum;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public CardTypeEnum getCardTypeEnum() {
        return cardTypeEnum;
    }

    public void setCardTypeEnum(CardTypeEnum cardTypeEnum) {
        this.cardTypeEnum = cardTypeEnum;
    }

    public SourceEnum getSourceEnum() {
        return sourceEnum;
    }

    public void setSourceEnum(SourceEnum sourceEnum) {
        this.sourceEnum = sourceEnum;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Long getTradeAreaId() {
        return tradeAreaId;
    }

    public void setTradeAreaId(Long tradeAreaId) {
        this.tradeAreaId = tradeAreaId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public CouponStatusEnum getCouponStatusEnum() {
        return couponStatusEnum;
    }

    public void setCouponStatusEnum(CouponStatusEnum couponStatusEnum) {
        this.couponStatusEnum = couponStatusEnum;
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
