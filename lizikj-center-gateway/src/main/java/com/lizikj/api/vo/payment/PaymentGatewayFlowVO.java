package com.lizikj.api.vo.payment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- payment_gateway_flow Created by lijundong on 2017-34-27
 * 17:34:01
 */
@ApiModel
public class PaymentGatewayFlowVO {
	
	/**
	 * 请求流水ID
	 */
	@ApiModelProperty(value = "请求流水ID")
	private Long id;

	/**
	 * 支付流水ID
	 */
	@ApiModelProperty(value = "")
	private Long payFlowId;

	/**
	 * 通道code
	 */
	@ApiModelProperty(value = "支付通道")
	private Byte channelCode;

	/**
	 * 支付方式代码
	 */
	@ApiModelProperty(value = "支付方式")
	private Byte paymentTypeCode;

	/**
	 * 支付场景代码
	 */
	@ApiModelProperty(value = "支付场景")
	private Byte sceneCode;

	/**
	 * 请求类型 1：统一下单 2：查询 3：退款 4：退款查询 5：撤销订单 6：下载对账单 7：银行卡交易结果查询
	 */
	@ApiModelProperty(value = "请求类型 1：统一下单 2：查询 3：退款 4：退款查询 5：撤销订单 6：下载对账单 7：银行卡交易结果查询")
	private Byte requestType;

	/**
	 * 请求结果 1：成功 2：失败
	 */
	@ApiModelProperty(value = "")
	private Byte result;

	/**
	 * 失败原因
	 */
	@ApiModelProperty(value = "失败原因")
	private String failReason;

	/**
	 * 流水详情
	 */
	@ApiModelProperty(value = "流水详情")
	private PaymentGatewayFlowDetailVO detail;

	/**
	 * 请求流水ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 请求流水ID
	 * 
	 * @param id
	 *            the value for payment_gateway_flow.id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 支付流水ID
	 */
	public Long getPayFlowId() {
		return payFlowId;
	}

	/**
	 * 支付流水ID
	 * 
	 * @param payFlowId
	 *            the value for payment_gateway_flow.pay_flow_id
	 */
	public void setPayFlowId(Long payFlowId) {
		this.payFlowId = payFlowId;
	}

	/**
	 * 通道code
	 */
	public Byte getChannelCode() {
		return channelCode;
	}

	/**
	 * 通道code
	 * 
	 * @param channelCode
	 *            the value for payment_gateway_flow.channel_code
	 */
	public void setChannelCode(Byte channelCode) {
		this.channelCode = channelCode;
	}

	/**
	 * 请求类型 1：统一下单 2：查询 3：退款 4：退款查询
	 */
	public Byte getRequestType() {
		return requestType;
	}

	/**
	 * 请求类型 1：统一下单 2：查询 3：退款 4：退款查询
	 * 
	 * @param requestType
	 *            the value for payment_gateway_flow.request_type
	 */
	public void setRequestType(Byte requestType) {
		this.requestType = requestType;
	}

	/**
	 * 请求结果 1：成功 2：失败
	 */
	public Byte getResult() {
		return result;
	}

	/**
	 * 请求结果 1：成功 2：失败
	 * 
	 * @param result
	 *            the value for payment_gateway_flow.result
	 */
	public void setResult(Byte result) {
		this.result = result;
	}

	/**
	 * 失败原因
	 */
	public String getFailReason() {
		return failReason;
	}

	/**
	 * 失败原因
	 * 
	 * @param failReason
	 *            the value for payment_gateway_flow.fail_reason
	 */
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public Byte getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(Byte paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public Byte getSceneCode() {
		return sceneCode;
	}

	public void setSceneCode(Byte sceneCode) {
		this.sceneCode = sceneCode;
	}

	public PaymentGatewayFlowDetailVO getDetail() {
		return detail;
	}

	public void setDetail(PaymentGatewayFlowDetailVO detail) {
		this.detail = detail;
	}

}