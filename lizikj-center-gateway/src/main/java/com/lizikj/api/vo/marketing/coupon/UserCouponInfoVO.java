package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户券vo
 * @auth zone
 * @date 2017-12-12
 */
public class UserCouponInfoVO {

	@ApiModelProperty(required = true, name = "userCouponId", value = "用户券ID", dataType = "Long")
	private Long userCouponId;

	@ApiModelProperty(required = true, name = "couponId", value = "券ID", dataType = "Long")
	private Long couponId;
	
	@ApiModelProperty(required = true, name = "couponCode", value = "券码", dataType = "String")
	private String couponCode;
	
	@ApiModelProperty(required = true, name = "merchantId", value = "商户ID", dataType = "Long")
	private Long merchantId;
	
	@ApiModelProperty(required = true, name = "shopId", value = "店铺ID", dataType = "Long")
	private Long shopId;
	
	@ApiModelProperty(required = true, name = "couponType", value = "券类型 \n 1：代金券\n 2：其它", dataType = "byte")
	private byte couponType;

	@ApiModelProperty(required = true, name = "couponName", value = "券名称", dataType = "String")
	private String couponName;

	@ApiModelProperty(required = true, name = "faceValue", value = "面额", dataType = "Long")
	private Long faceValue;

	@ApiModelProperty(required = true, name = "beginTime", value = "有效期起 yyyy.MM.dd", dataType = "String")
	private String beginTime;

	@ApiModelProperty(required = true, name = "endTime", value = "有效期止 yyyy.MM.dd", dataType = "String")
	private String endTime;
	
	@ApiModelProperty(required = true, name = "beginTimeStamp", value = "有效期起时间戳", dataType = "Long")
	private Long beginTimeStamp;
	
	@ApiModelProperty(required = true, name = "endTimeStamp", value = "有效期止时间戳", dataType = "Long")
	private Long endTimeStamp;

	@ApiModelProperty(required = true, name = "limitMoney", value = "最低消费金额 为0则表示不限制最低消费金额", dataType = "Long")
	private Long limitMoney;
	
	@ApiModelProperty(required = true, name = "canUse", value = "是否满足使用条件", dataType = "Boolean")
	private Boolean canUse;
	
	@ApiModelProperty(required = true, name = "chosen", value = "是否已勾选", dataType = "Boolean")
	private Boolean chosen = false;

	@ApiModelProperty(required = true, name = "isUsed", value = "是否已使用", dataType = "Boolean")
    private Boolean isUsed;

	@ApiModelProperty(required = true, name = "isOverTime", value = "是否已过期", dataType = "Boolean")
    private Boolean isOverTime;
    
	@ApiModelProperty(required = true, name = "effectiveStatus", value = "生效状态 0未开始    1可用    2已失效", dataType = "byte")
    private byte effectiveStatus;

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
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

	public byte getCouponType() {
		return couponType;
	}

	public void setCouponType(byte couponType) {
		this.couponType = couponType;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Long getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(Long faceValue) {
		this.faceValue = faceValue;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getLimitMoney() {
		return limitMoney;
	}

	public void setLimitMoney(Long limitMoney) {
		this.limitMoney = limitMoney;
	}

	public Boolean getCanUse() {
		return canUse;
	}

	public void setCanUse(Boolean canUse) {
		this.canUse = canUse;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}

	public Boolean getChosen() {
		return chosen;
	}

	public void setChosen(Boolean chosen) {
		this.chosen = chosen;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public Boolean getIsOverTime() {
		return isOverTime;
	}

	public void setIsOverTime(Boolean isOverTime) {
		this.isOverTime = isOverTime;
	}

	public Long getBeginTimeStamp() {
		return beginTimeStamp;
	}

	public void setBeginTimeStamp(Long beginTimeStamp) {
		this.beginTimeStamp = beginTimeStamp;
	}

	public Long getEndTimeStamp() {
		return endTimeStamp;
	}

	public void setEndTimeStamp(Long endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}

	public byte getEffectiveStatus() {
		return effectiveStatus;
	}

	public void setEffectiveStatus(byte effectiveStatus) {
		this.effectiveStatus = effectiveStatus;
	}

}
