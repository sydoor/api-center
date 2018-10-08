package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 砍价活动页面发起端参数对象
 * 
 * @author lijundong
 * @date 2017年9月8日 下午3:37:56
 */
@ApiModel(value = "发起砍价活动页面参数对象")
public class CutPageStartParamsVO {

	/**
	 * 订单编号
	 */
	@ApiModelProperty(required = true, name = "orderNo", value = "订单编号", dataType = "String")
	private String orderNo;

	/**
	 * 订单物品Id
	 */
	@ApiModelProperty(required = true, name = "orderItemId", value = "订单物品Id", dataType = "Long")
	private long orderItemId;

	/**
	 * 美食Id
	 */
	@ApiModelProperty(required = true, name = "goodsId", value = "美食Id", dataType = "String")
	private String goodsId;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Override
	public String toString() {
		return "CutPageStartParamsVO [orderNo=" + orderNo + ", orderItemId=" + orderItemId + ", goodsId=" + goodsId
				+ "]";
	}

}
