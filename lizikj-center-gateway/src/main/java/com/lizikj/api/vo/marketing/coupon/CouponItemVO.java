package com.lizikj.api.vo.marketing.coupon;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.marketing.api.dto.CouponItemDTO;
import com.lizikj.marketing.api.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户优惠券实体信息VO
 * @auth zone
 * @date 2018-07-12
 */
@ApiModel(value = "用户优惠券实体信息VO")
public class CouponItemVO implements Serializable {
	@ApiModelProperty(value = "实体ID")
	private Long itemId;
	@ApiModelProperty(value = "卡券编码")
	private String cardId;
	@ApiModelProperty(value = "卡券类型")
	private CardTypeEnum cardTypeEnum;
	@ApiModelProperty(value = "卡券来源")
	private SourceEnum sourceEnum;
	@ApiModelProperty(value = "使用卡券版本号")
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
	@ApiModelProperty(value = "使用状态")
	private CardUseEnum useStatusEnum;
	@ApiModelProperty(value = "领取时间")
	private Date drawTime;
	@ApiModelProperty(value = "使用时间")
	private Date useTime;
	@ApiModelProperty(value = "活动ID")
	private Long activityId;
	@ApiModelProperty(value = "卡券ID")
	private String couponName;
	@ApiModelProperty(value = "礼品券礼品")
	private String gifts;
	@ApiModelProperty(value = "折扣 10.00")
	private BigDecimal discount;
	@ApiModelProperty(value = "最低消费要求")
	private Long leastCost;
	@ApiModelProperty(value = "抵用券抵用费用")
	private Long reduceCost;
	@ApiModelProperty(value = "开始时间")
	private Date startTime;
	@ApiModelProperty(value = "结束时间")
	private Date endTime;
	@ApiModelProperty(value = "是否可赠送")
	private Boolean canShare;
	/**
	 * 卡券领取方式
	 */
	@ApiModelProperty(value = "卡券领取方式")
	private DrawMethodEnum drawMethodEnum;
	/**
	 * 卡券激活状态
	 */
	@ApiModelProperty(value = "卡券激活状态")
	private ActiveStatusEnum activeStatusEnum;
	@ApiModelProperty(value = "激活条件内容列表")
	List<ConditionAndUserVO> conditionAndUserVOS;
	@ApiModelProperty(value = "有效小时数")
	private Integer validHour;
	@ApiModelProperty(value = "到期时间")
	private Date expireTime;
	@ApiModelProperty(value = "邀请码")
	private PlayCodeEnum playCodeEnum;
	@ApiModelProperty(value = "是否推荐使用")
	private Boolean promoteUse;
	@ApiModelProperty(value = "使用规则")
	private RulePayloadVO usePayload;


	public static CouponItemVO from(CouponItemDTO dto){
		CouponItemVO vo = ObjectConvertUtil.map(dto,CouponItemVO.class);
		try{
			vo.setUsePayload(JSONObject.parseObject(dto.getUseRuleInfo(),RulePayloadVO.class));
		}catch (Exception e){
			//可以忽略
		}

		return vo;
	}

	public static List<CouponItemVO> from(List<CouponItemDTO> dtos){
		List<CouponItemVO> vos = new ArrayList<>();
		if(dtos != null){
			dtos.forEach(t -> vos.add(from(t)));
		}

		return vos;
	}

	public RulePayloadVO getUsePayload() {
		return usePayload;
	}

	public void setUsePayload(RulePayloadVO usePayload) {
		this.usePayload = usePayload;
	}

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

	public Date getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
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

	public DrawMethodEnum getDrawMethodEnum() {
		return drawMethodEnum;
	}

	public void setDrawMethodEnum(DrawMethodEnum drawMethodEnum) {
		this.drawMethodEnum = drawMethodEnum;
	}

	public ActiveStatusEnum getActiveStatusEnum() {
		return activeStatusEnum;
	}

	public void setActiveStatusEnum(ActiveStatusEnum activeStatusEnum) {
		this.activeStatusEnum = activeStatusEnum;
	}

	public Boolean getCanShare() {
		return canShare;
	}

	public void setCanShare(Boolean canShare) {
		this.canShare = canShare;
	}

	public List<ConditionAndUserVO> getConditionAndUserVOS() {
		return conditionAndUserVOS;
	}

	public void setConditionAndUserVOS(List<ConditionAndUserVO> conditionAndUserVOS) {
		this.conditionAndUserVOS = conditionAndUserVOS;
	}

	public Integer getValidHour() {
		return validHour;
	}

	public void setValidHour(Integer validHour) {
		this.validHour = validHour;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public PlayCodeEnum getPlayCodeEnum() {
		return playCodeEnum;
	}

	public void setPlayCodeEnum(PlayCodeEnum playCodeEnum) {
		this.playCodeEnum = playCodeEnum;
	}

	public Boolean getPromoteUse() {
		return promoteUse;
	}

	public void setPromoteUse(Boolean promoteUse) {
		this.promoteUse = promoteUse;
	}
}
