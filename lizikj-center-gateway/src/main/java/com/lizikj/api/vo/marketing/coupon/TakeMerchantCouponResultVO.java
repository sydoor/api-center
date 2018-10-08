package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @auth zone
 * @date 2017-12-12
 */
@ApiModel(value = "领券结果VO")
public class TakeMerchantCouponResultVO {
	@ApiModelProperty(required = true, name = "success", value = "领券结果", dataType = "boolean")
	private boolean success;

	@ApiModelProperty(required = true, name = "message", value = "领券失败的原因", dataType = "String")
	private String message;
	
	@ApiModelProperty(required = true, name = "merchantCouponInfo", value = "券详情", dataType = "MerchantCouponInfoVO")
	private MerchantCouponInfoVO merchantCouponInfo;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MerchantCouponInfoVO getMerchantCouponInfo() {
		return merchantCouponInfo;
	}
	public void setMerchantCouponInfo(MerchantCouponInfoVO merchantCouponInfo) {
		this.merchantCouponInfo = merchantCouponInfo;
	}

}
