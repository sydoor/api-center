package com.lizikj.api.vo.marketing.coupon;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.marketing.api.dto.CouponDTO;
import com.lizikj.marketing.api.enums.CardTypeEnum;
import com.lizikj.marketing.api.enums.CouponStatusEnum;
import com.lizikj.marketing.api.enums.PlayCodeEnum;
import com.lizikj.marketing.api.enums.SourceEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @Date 2018/7/30 15:35
 */
@ApiModel(value = "卡券")
public class CouponVO {
    /**
     * 卡券ID
     */
    @ApiModelProperty(value = "卡券ID")
    private String cardId;
    /**
     * 卡券版本
     */
    @ApiModelProperty(value = "卡券版本")
    private Long cardVersion;
    /**
     * 活动ID
     */
    @ApiModelProperty(value = "活动ID")
    private Long activityId;
    /**
     * 卡券类型
     */
    @ApiModelProperty(value = "卡券类型")
    private CardTypeEnum cardType;
    /**
     * 卡券来源
     */
    @ApiModelProperty(value = "卡券来源")
    private SourceEnum cardSource;
    /**
     * 卡券名称
     */
    @ApiModelProperty(value = "卡券名称")
    private String couponName;
    /**
     * 商圈ID
     */
    @ApiModelProperty(value = "商圈ID")
    private Long tradeAreaId;
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "有效小时数")
    private Integer validHour;

    /**
     * 规则JSON
     */
    private RulePayloadVO ruleInfoVO;

    /**
     *使用 规则JSON
     */
    private RulePayloadVO useRuleInfoVO;
    /**
     * 礼品 多个礼品用逗号隔开
     */
    @ApiModelProperty(value = "礼品 多个礼品用逗号隔开")
    private String gifts;
    /**
     * 库存
     */
    @ApiModelProperty(value = "库存")
    private Integer stock;
    /**
     * 发送数量
     */
    @ApiModelProperty(value = "发送数量")
    private Integer grantNum;
    /**
     * 使用数量
     */
    @ApiModelProperty(value = "使用数量")
    private Integer useNum;
    /**
     * 折扣
     */
    @ApiModelProperty(value = "折扣")
    private BigDecimal discount;
    /**
     * 最低消费
     */
    @ApiModelProperty(value = "最低消费")
    private Long leastCost;
    /**
     * 抵扣金额
     */
    @ApiModelProperty(value = "抵扣金额")
    private Long reduceCost;
    /**
     * 卡券状态
     */
    @ApiModelProperty(value = "卡券状态")
    private CouponStatusEnum couponStatus;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "是否可赠送")
    private Boolean canShare;
    /**
     * 邀请码
     */
    @ApiModelProperty(value = "发放途径")
    private PlayCodeEnum playCodeEnum;

    public CouponDTO convertToDTO(){
        CouponDTO dto = ObjectConvertUtil.map(this,CouponDTO.class);
        if (getUseRuleInfoVO() != null) {
            dto.setUseRuleInfo(JSONObject.toJSONString(getUseRuleInfoVO()));
        }
        if (getRuleInfoVO() != null) {
            dto.setRuleInfo(JSONObject.toJSONString(getRuleInfoVO()));
        }

        return dto;
    }

    public static CouponVO from(CouponDTO source){
        CouponVO coupon = ObjectConvertUtil.map(source, CouponVO.class);
        if (source.getUseRuleInfo() != null) {
            coupon.setUseRuleInfoVO(JSONObject.parseObject(source.getUseRuleInfo(),RulePayloadVO.class));
        }
        if (source.getRuleInfo() != null) {
            coupon.setRuleInfoVO(JSONObject.parseObject(source.getRuleInfo(),RulePayloadVO.class));
        }

        return coupon;
    }

    public static List<CouponVO> from(List<CouponDTO> sources){
        List<CouponVO> vos = new ArrayList<>();
        if(sources != null){
            sources.forEach(t -> {
                vos.add(from(t));
            });
        }

        return vos;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Long getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(Long cardVersion) {
        this.cardVersion = cardVersion;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public CardTypeEnum getCardType() {
        return cardType;
    }

    public void setCardType(CardTypeEnum cardType) {
        this.cardType = cardType;
    }

    public SourceEnum getCardSource() {
        return cardSource;
    }

    public void setCardSource(SourceEnum cardSource) {
        this.cardSource = cardSource;
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

    public RulePayloadVO getRuleInfoVO() {
        return ruleInfoVO;
    }

    public void setRuleInfoVO(RulePayloadVO ruleInfoVO) {
        this.ruleInfoVO = ruleInfoVO;
    }

    public RulePayloadVO getUseRuleInfoVO() {
        return useRuleInfoVO;
    }

    public void setUseRuleInfoVO(RulePayloadVO useRuleInfoVO) {
        this.useRuleInfoVO = useRuleInfoVO;
    }

    public String getGifts() {
        return gifts;
    }

    public void setGifts(String gifts) {
        this.gifts = gifts;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getGrantNum() {
        return grantNum;
    }

    public void setGrantNum(Integer grantNum) {
        this.grantNum = grantNum;
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
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

    public CouponStatusEnum getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(CouponStatusEnum couponStatus) {
        this.couponStatus = couponStatus;
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

    public Integer getValidHour() {
        return validHour;
    }

    public void setValidHour(Integer validHour) {
        this.validHour = validHour;
    }

    public Boolean getCanShare() {
        return canShare;
    }

    public void setCanShare(Boolean canShare) {
        this.canShare = canShare;
    }

    public PlayCodeEnum getPlayCodeEnum() {
        return playCodeEnum;
    }

    public void setPlayCodeEnum(PlayCodeEnum playCodeEnum) {
        this.playCodeEnum = playCodeEnum;
    }
}
