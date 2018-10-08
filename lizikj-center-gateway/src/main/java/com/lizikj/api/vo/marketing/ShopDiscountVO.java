package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "店铺优惠信息")
public class ShopDiscountVO {

	/**
	 * 店铺优惠类型
	 */
	@ApiModelProperty(required = true, name = "type", value = "店铺优惠类型, 1=会员优惠，2=砍价活动", dataType = "Integer")
	private int type;

	/**
	 * 优惠描述
	 */
	@ApiModelProperty(required = true, name = "describe", value = "优惠描述", dataType = "String")
	private String describe;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
