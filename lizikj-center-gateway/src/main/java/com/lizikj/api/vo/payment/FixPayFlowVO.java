package com.lizikj.api.vo.payment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class FixPayFlowVO {

	/**
	 * 内部支付流水号
	 */
	@ApiModelProperty(required = true, name = "innerTradeNo", value = "内部支付流水号")
	private String innerTradeNo;

	/**
	 * 退款状态
	 */
	@ApiModelProperty(name = "refundStatus", value = "退款状态")
	private Byte refundStatus;

	/**
	 * 支付金额
	 */
	@ApiModelProperty(name = "amount", value = "支付金额")
	private Long amount;

	public String getInnerTradeNo() {
		return innerTradeNo;
	}

	public void setInnerTradeNo(String innerTradeNo) {
		this.innerTradeNo = innerTradeNo;
	}

	public Byte getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Byte refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "FixPayFlowVO [innerTradeNo=" + innerTradeNo + ", refundStatus=" + refundStatus + ", amount=" + amount
				+ "]";
	}

}
