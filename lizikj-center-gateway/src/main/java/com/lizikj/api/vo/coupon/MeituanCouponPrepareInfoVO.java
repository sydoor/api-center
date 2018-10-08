package com.lizikj.api.vo.coupon;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 团购券预验券信息 VO
 * @auth zone
 * @date 2017-11-28
 */
@ApiModel(value = "团购券预验券信息 VO")
public class MeituanCouponPrepareInfoVO {
	@ApiModelProperty(required = true, name = "couponId", value = "团购券ID", dataType = "Long")
	private Long couponId;
	
	@ApiModelProperty(required = true, name = "facePrice", value = "面值,即人们常说的市场价", dataType = "Long")
	private Long facePrice;
	
	@ApiModelProperty(required = true, name = "buyPrice", value = "券购买价,即用户在购买团购券时所付的实际价格", dataType = "Long")
	private Long buyPrice;

	@ApiModelProperty(required = true, name = "couponCode", value = "券码", dataType = "String")
	private String couponCode;

	@ApiModelProperty(required = true, name = "endTime", value = "券码有效期", dataType = "Date")
	private Date endTime;

	@ApiModelProperty(required = true, name = "title", value = "券名称", dataType = "String")
	private String title;

	@ApiModelProperty(required = true, name = "maxCount", value = "最大可验证张数", dataType = "Integer")
	private Integer maxCount;

	@ApiModelProperty(required = true, name = "minCount", value = "最小消费张数", dataType = "Integer")
	private Integer minCount;

	@ApiModelProperty(required = true, name = "couponType", value = "优惠券类型 1:代金券   2:套餐券", dataType = "byte")
	private byte couponType;

	@ApiModelProperty(required = true, name = "canUse", value = "能否使用", dataType = "Boolean")
	private Boolean canUse = true;

	@ApiModelProperty(required = true, name = "success", value = "预验券是否成功", dataType = "Boolean")
	private Boolean success = true;

	@ApiModelProperty(required = true, name = "message", value = "验券结果信息", dataType = "String")
	private String message;
	
	public Long getCouponId() {
		return couponId;
	}
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getFacePrice() {
		return facePrice;
	}
	public void setFacePrice(Long facePrice) {
		this.facePrice = facePrice;
	}
	public Long getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Long buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}
	public Integer getMinCount() {
		return minCount;
	}
	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}
	public byte getCouponType() {
		return couponType;
	}
	public void setCouponType(byte couponType) {
		this.couponType = couponType;
	}
	public Boolean getCanUse() {
		return canUse;
	}
	public void setCanUse(Boolean canUse) {
		this.canUse = canUse;
	}
}
