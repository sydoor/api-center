package com.lizikj.api.vo.marketing;

import com.lizikj.api.vo.marketing.coupon.MerchantCouponInfoVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 帮忙砍价结果类
 * 
 * @author lijundong
 * @date 2017年9月11日 下午2:26:40
 */
@ApiModel(value = "帮忙砍价结果对象")
public class HelpCutResultVO {

	/**
	 * 已砍金额
	 */
	@ApiModelProperty(required = true, name = "cutAmount", value = "已砍金额", dataType = "Long")
	private long cutAmount;

	/**
	 * 已砍比例
	 */
	@ApiModelProperty(required = true, name = "cutPercent", value = "已砍比例", dataType = "String")
	private String cutPercent;

	/**
	 * 领取优惠券详情
	 */
	@ApiModelProperty(required = false, name = "merchantCouponInfoVO", value = "领取优惠券详情", dataType = "Object")
	private MerchantCouponInfoVO merchantCouponInfoVO;

	public long getCutAmount() {
		return cutAmount;
	}

	public void setCutAmount(long cutAmount) {
		this.cutAmount = cutAmount;
	}

	public String getCutPercent() {
		return cutPercent;
	}

	public void setCutPercent(String cutPercent) {
		this.cutPercent = cutPercent;
	}

	public MerchantCouponInfoVO getMerchantCouponInfoVO() {
		return merchantCouponInfoVO;
	}

	public void setMerchantCouponInfoVO(MerchantCouponInfoVO merchantCouponInfoVO) {
		this.merchantCouponInfoVO = merchantCouponInfoVO;
	}

}
