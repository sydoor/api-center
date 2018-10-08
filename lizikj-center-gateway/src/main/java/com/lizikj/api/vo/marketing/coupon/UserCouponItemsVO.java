package com.lizikj.api.vo.marketing.coupon;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户优惠券信息VO
 * @auth zone
 * @date 2018-07-12
 */
@ApiModel(value = "用户优惠券信息VO")
public class UserCouponItemsVO implements Serializable {
	@ApiModelProperty(required = true, name = "count", value = "券总数", dataType = "Integer")
	private int count;
	@ApiModelProperty(required = true, name = "prizePoolItemList", value = "券列表", dataType = "List")
	private List<CouponItemVO> prizePoolItemList;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<CouponItemVO> getPrizePoolItemList() {
		return prizePoolItemList;
	}
	public void setPrizePoolItemList(List<CouponItemVO> prizePoolItemList) {
		this.prizePoolItemList = prizePoolItemList;
	}
}
