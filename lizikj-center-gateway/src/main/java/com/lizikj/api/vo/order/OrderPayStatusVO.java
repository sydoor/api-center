package com.lizikj.api.vo.order;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单支付结果VO
 * @author lijundong
 * @date 2017年10月24日 下午5:37:20
 */
@ApiModel
public class OrderPayStatusVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4732397021369408049L;

	@ApiModelProperty(value = "订单编号", required = true, dataType="String")
	private String orderNo;

	@ApiModelProperty(value = "支付状态,0=未支付,1=支付进行中,2=部分支付成功,3=支付成功,4=支付失败,5=取消支付", required = true, dataType="Integer")
	private byte payStatus;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(byte payStatus) {
		this.payStatus = payStatus;
	}

}
