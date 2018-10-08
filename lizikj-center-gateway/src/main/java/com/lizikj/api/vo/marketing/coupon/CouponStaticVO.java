package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.CardTypeEnum;
import com.lizikj.marketing.api.enums.SourceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lxl
 * @Date 2018/7/25 11:23
 */
@ApiModel(value = "卡券统计")
public class CouponStaticVO {
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "卡券ID")
    private String cardId;
    @ApiModelProperty(value = "卡券使用版本")
    private Long userVersion;
    @ApiModelProperty(value = "卡券类型")
    private CardTypeEnum cardTypeEnum;
    @ApiModelProperty(value = "卡券来源")
    private SourceEnum sourceEnum;
    @ApiModelProperty(value = "礼品券礼品")
    private String gifts;
    @ApiModelProperty(value = "折扣 10.00")
    private BigDecimal discount;
    @ApiModelProperty(value = "最低消费要求")
    private Long leastCost;
    @ApiModelProperty(value = "抵用券抵用费用")
    private Long reduceCost;
    /**
     * 卡券名称
     */
    @ApiModelProperty(value = "卡券名称")
    private String couponName;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "卡券数量")
    private Integer num;
    @ApiModelProperty(value = "快过期数量")
    private Integer soonExpireNum = Integer.valueOf(0);

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Long getUserVersion() {
        return userVersion;
    }

    public void setUserVersion(Long userVersion) {
        this.userVersion = userVersion;
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

    public String getGifts() {
        return gifts;
    }

    public void setGifts(String gifts) {
        this.gifts = gifts;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Long getLeastCost() {
        return leastCost;
    }

    public void setLeastCost(Long leastCost) {
        this.leastCost = leastCost;
    }

    public Long getReduceCost() {
        return reduceCost;
    }

    public void setReduceCost(Long reduceCost) {
        this.reduceCost = reduceCost;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getSoonExpireNum() {
        return soonExpireNum;
    }

    public void setSoonExpireNum(Integer soonExpireNum) {
        this.soonExpireNum = soonExpireNum;
    }
}
