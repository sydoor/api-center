package com.lizikj.api.vo.marketing.coupon;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商家添加优惠券VO
 * @auth zone
 * @date 2017-12-12
 */
@ApiModel(value = "商家添加优惠券VO")
public class MerchantCouponAddParamVO {
	@ApiModelProperty(required = true, name = "couponType", value = "券类型 \n 1：代金券\n 2：其它", dataType = "byte")
	private byte couponType;

	@ApiModelProperty(required = true, name = "couponName", value = "券名称", dataType = "String")
	private String couponName;

	@ApiModelProperty(required = true, name = "faceValue", value = "面额", dataType = "Long")
	private Long faceValue;

	@ApiModelProperty(required = true, name = "totalNum", value = "总张数", dataType = "Integer")
	private Integer totalNum;

	@ApiModelProperty(required = true, name = "beginTime", value = "有效期起 yyyy-MM-dd", dataType = "Date")
	private Date beginTime;

	@ApiModelProperty(required = true, name = "endTime", value = "有效期止 yyyy-MM-dd", dataType = "Date")
	private Date endTime;

	@ApiModelProperty(required = true, name = "limitMoney", value = "最低消费金额 为0则表示不限制最低消费金额", dataType = "Long")
	private Long limitMoney;

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
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getLimitMoney() {
		return limitMoney;
	}
	public void setLimitMoney(Long limitMoney) {
		this.limitMoney = limitMoney;
	}
	
}
