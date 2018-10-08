package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.CardTypeEnum;
import com.lizikj.marketing.api.enums.CardUseEnum;
import com.lizikj.marketing.api.enums.SourceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/7/25 11:07
 */
@ApiModel(value = "卡券实体查询条件")
public class CouponItemQueryParamVO {
    @ApiModelProperty(value = "实体ID")
    private Long itemId;
    @ApiModelProperty(value = "卡券ID")
    private String cardId;
    @ApiModelProperty(value = "卡券类型")
    private CardTypeEnum cardTypeEnum;
    @ApiModelProperty(value = "卡券来源")
    private SourceEnum sourceEnum;
    @ApiModelProperty(value = "使用版本")
    private Long useVersion;
    @ApiModelProperty(value = "卡券码")
    private String code;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "卡券使用状态")
    private CardUseEnum useStatusEnum;
    @ApiModelProperty(value = "领取开始时间")
    private Date startDrawTime;
    @ApiModelProperty(value = "领取结束时间")
    private Date endDrawTime;
    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public Long getUseVersion() {
        return useVersion;
    }

    public void setUseVersion(Long useVersion) {
        this.useVersion = useVersion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTradeAreaId() {
        return tradeAreaId;
    }

    public void setTradeAreaId(Long tradeAreaId) {
        this.tradeAreaId = tradeAreaId;
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

    public CardUseEnum getUseStatusEnum() {
        return useStatusEnum;
    }

    public void setUseStatusEnum(CardUseEnum useStatusEnum) {
        this.useStatusEnum = useStatusEnum;
    }

    public Date getStartDrawTime() {
        return startDrawTime;
    }

    public void setStartDrawTime(Date startDrawTime) {
        this.startDrawTime = startDrawTime;
    }

    public Date getEndDrawTime() {
        return endDrawTime;
    }

    public void setEndDrawTime(Date endDrawTime) {
        this.endDrawTime = endDrawTime;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
