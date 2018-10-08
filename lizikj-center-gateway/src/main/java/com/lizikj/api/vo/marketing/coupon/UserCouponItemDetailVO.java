package com.lizikj.api.vo.marketing.coupon;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lizikj.common.util.ObjectConvertUtil;
import com.lizikj.marketing.api.dto.UserCouponItemDetailDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @auth zone
 * @date 2018-07-12
 */
@ApiModel(value = "用户优惠券详细信息VO")
public class UserCouponItemDetailVO implements Serializable {
	@ApiModelProperty(name = "code", value = "券码", dataType = "String")
	private String code;
	@ApiModelProperty(name = "itemId", value = "实体券id", dataType = "Long")
	private Long itemId;
	@ApiModelProperty(name = "cardType", value = "券类型   100:代金券   101:红包   102:折扣券    103:满减券     104:礼品券", dataType = "Long")
	private Integer cardType;
	@ApiModelProperty(name = "cardSource", value = "卡券来源 1:平台   2:商户  3:门店    4:商圈", dataType = "Byte")
	private Byte cardSource;
	@ApiModelProperty(name = "couponName", value = "券名", dataType = "String")
	private String couponName;
	@ApiModelProperty(name = "ruleInfo", value = "规则信息", dataType = "String")
	private String ruleInfo;
	@ApiModelProperty(name = "gifts", value = "礼品信息", dataType = "String")
	private String gifts;
	@ApiModelProperty(name = "discount", value = "折扣", dataType = "Long")
	private BigDecimal discount;
	@ApiModelProperty(name = "leastCost", value = "最低消费", dataType = "Long")
	private Long leastCost;
	@ApiModelProperty(name = "reduceCost", value = "抵扣金额", dataType = "Long")
	private Long reduceCost;
	@ApiModelProperty(name = "couponStatus", value = "券状态 1:启用   0:停用", dataType = "Byte")
	private Byte couponStatus;
	@ApiModelProperty(name = "startTime", value = "有效期起", dataType = "Long")
	private Long startTime;
	@ApiModelProperty(name = "endTime", value = "有效期止", dataType = "Long")
	private Long endTime;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public Byte getCardSource() {
		return cardSource;
	}
	public void setCardSource(Byte cardSource) {
		this.cardSource = cardSource;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getRuleInfo() {
		return ruleInfo;
	}
	public void setRuleInfo(String ruleInfo) {
		this.ruleInfo = ruleInfo;
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
	public Byte getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(Byte couponStatus) {
		this.couponStatus = couponStatus;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
}
