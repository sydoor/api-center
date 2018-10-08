package com.lizikj.api.vo.marketing.coupon;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商家添加优惠券VO
 * @auth zone
 * @date 2017-12-12
 */
@ApiModel(value = "商家优惠券VO")
public class MerchantCouponInfoVO {
	@ApiModelProperty(required = true, name = "couponId", value = "券ID", dataType = "Long")
	private Long couponId;
	
	@ApiModelProperty(required = true, name = "merchantId", value = "商户ID", dataType = "Long")
	private Long merchantId;
	
	@ApiModelProperty(required = true, name = "shopId", value = "店铺ID", dataType = "Long")
	private Long shopId;
	
	@ApiModelProperty(required = true, name = "couponType", value = "券类型 \n 1：代金券\n 2：其它", dataType = "Integer")
	private Byte couponType;

	@ApiModelProperty(required = true, name = "couponName", value = "券名称", dataType = "String")
	private String couponName;

	@ApiModelProperty(required = true, name = "faceValue", value = "面额", dataType = "Long")
	private Long faceValue;

	@ApiModelProperty(required = true, name = "totalNum", value = "总张数", dataType = "Integer")
	private Integer totalNum;
	
	@ApiModelProperty(required = true, name = "tookNum", value = "已领取数量", dataType = "Integer")
	private Integer tookNum;
	
	@ApiModelProperty(required = true, name = "usedNum", value = "已使用数量", dataType = "Integer")
	private Integer usedNum;

	@ApiModelProperty(required = true, name = "beginTime", value = "有效期起 时间戳", dataType = "Long")
	private Long beginTime;

	@ApiModelProperty(required = true, name = "endTime", value = "有效期止 时间戳", dataType = "Long")
	private Long endTime;

	@ApiModelProperty(required = true, name = "limitMoney", value = "最低消费金额 为0则表示不限制最低消费金额", dataType = "Long")
	private Long limitMoney;
	
	@ApiModelProperty(required = true, name = "effectiveStatus", value = "生效状态 \n 1:生效 \n 2:已失效", dataType = "byte")
	private Byte effectiveStatus;
	
	@ApiModelProperty(required = true, name = "cancelStatus", value = "撤下状态 \n 0：未撤下\n 1：已撤下", dataType = "byte")
	private Byte cancelStatus;

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
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Long getLimitMoney() {
		return limitMoney;
	}
	public void setLimitMoney(Long limitMoney) {
		this.limitMoney = limitMoney;
	}
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
	public Integer getTookNum() {
		return tookNum;
	}
	public void setTookNum(Integer tookNum) {
		this.tookNum = tookNum;
	}
	public Integer getUsedNum() {
		return usedNum;
	}
	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}
	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Byte getEffectiveStatus() {
		return effectiveStatus;
	}
	public void setEffectiveStatus(Byte effectiveStatus) {
		this.effectiveStatus = effectiveStatus;
	}
	public Byte getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(Byte cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	
}
