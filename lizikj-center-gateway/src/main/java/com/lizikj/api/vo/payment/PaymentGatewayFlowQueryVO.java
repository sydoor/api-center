package com.lizikj.api.vo.payment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 第三方支付网关流水查询类
 * 
 * @author lijundong
 * @date 2017年11月28日 下午5:32:00
 */
@ApiModel
public class PaymentGatewayFlowQueryVO {

	/**
	 * 支付流水id
	 */
	@ApiModelProperty(value = "支付流水id")
	private Long payFlowId;

	/**
	 * 内部支付流水号
	 */
	@ApiModelProperty(value = "内部支付流水号")
	private String innerTradeNo;

	/**
	 * 请求类型
	 */
	@ApiModelProperty(value = "请求类型,1=统一下单,2=下单查询,3=统一退款,4=退款查询,5=撤销下单")
	private Byte requestType;

	/**
	 * 支付通道枚举
	 */
	@ApiModelProperty(name = "channelCode", value = "支付通道:1=钱宝,2=通联3=兴业,4=银盛,5=群迈,6=李子,7=汇宜，8=中汇", dataType = "Integer")
	private Byte channelCode;

	/**
	 * 支付方式枚举
	 */
	@ApiModelProperty(name = "paymentTypeCode", value = "支付方式:1=支付宝,2=微信,3=银联钱包,4=银行卡支付,5=现金,8=会员，9=优惠券，10=美团券，12=预付券", dataType = "Integer")
	private Byte paymentTypeCode;

	/**
	 * 支付场景
	 */
	@ApiModelProperty(name = "sceneCode", value = "支付场景:1=收银台付款码,2=店铺收款码,3=订单小票支付二维码,4=pos扫用户,5=h5点餐在线支付,7=刷卡，8=app支付，9=云闪付，10=现金", dataType = "Integer")
	private Byte sceneCode;

	public Long getPayFlowId() {
		return payFlowId;
	}

	public void setPayFlowId(Long payFlowId) {
		this.payFlowId = payFlowId;
	}

	public String getInnerTradeNo() {
		return innerTradeNo;
	}

	public void setInnerTradeNo(String innerTradeNo) {
		this.innerTradeNo = innerTradeNo;
	}

	public Byte getRequestType() {
		return requestType;
	}

	public void setRequestType(Byte requestType) {
		this.requestType = requestType;
	}

	public Byte getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(Byte channelCode) {
		this.channelCode = channelCode;
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

}
