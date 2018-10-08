package com.lizikj.api.vo.payment;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PayFlowQueryVO {

	/**
	 * 商户Id
	 */
	@ApiModelProperty(name = "merchantId", value = "商户Id", dataType = "Integer")
	private Long merchantId;

	/**
	 * 店铺Id
	 */
	@ApiModelProperty(name = "shopId", value = "店铺Id", dataType = "Integer")
	private Long shopId;

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

	/**
	 * 支付状态
	 */
	@ApiModelProperty(name = "payStatus:0=未支付,1=支付进行中,2=部分支付成功,3=支付成功,4=支付失败,5=取消支付", value = "支付状态", dataType = "String")
	private Byte payStatus;

	/**
	 * 商户会员Id
	 */
	@ApiModelProperty(name = "memberId", value = "商户会员Id", dataType = "Integer")
	private Long memberId;

	/**
	 * 李子会员Id
	 */
	@ApiModelProperty(name = "merchantMemberId", value = "李子会员Id", dataType = "Integer")
	private Long merchantMemberId;

	/**
	 * 开始时间
	 */
	@ApiModelProperty(name = "startTime", value = "开始时间", dataType = "Date")
	private Date startTime;

	/**
	 * 结束时间
	 */
	@ApiModelProperty(name = "endTime", value = "结束时间", dataType = "Date")
	private Date endTime;

	/**
	 * 订单编号
	 */
	@ApiModelProperty(name = "orderNo", value = "订单编号", dataType = "String")
	private String orderNo;

	/**
	 * 内部交易号
	 */
	@ApiModelProperty(name = "innerTradeNo", value = "内部交易号", dataType = "String")
	private String innerTradeNo;

	/**
	 * sn号码
	 */
	@ApiModelProperty(name = "snNum", value = "sn号码", dataType = "String")
	private String snNum;

	/**
	 * 付款人id
	 */
	@ApiModelProperty(name = "userId", value = "付款人id", dataType = "Integer")
	private Long userId;

	/**
	 * 收银员id
	 */
	@ApiModelProperty(name = "cashierId", value = "收银员id", dataType = "Integer")
	private Long cashierId;

	/**
	 * 业务类型,1=点餐订单，2=快捷收银，3=会员充值，4=增值服务，5=股东会议充值
	 */
	@ApiModelProperty(name = "bizType", value = "业务类型,1=点餐订单，2=快捷收银，3=会员充值，4=增值服务，5=股东会员充值", dataType = "Integer")
	private Byte bizType;

	/**
	 * 商户名称
	 */
	@ApiModelProperty(name = "merchantName", value = "商户名称", dataType = "String")
	private String merchantName;

	/**
	 * 店铺名称
	 */
	@ApiModelProperty(name = "shopName", value = "店铺名称", dataType = "String")
	private String shopName;

	/**
	 * 商户会员Id集合, 用户in查询
	 */
	/*
	 * private List<Long> memberIds;
	 * 
	 *//**
		 * 李子会员Id集合, 用户in查询
		 */
	/*
	 * private List<Long> merchantMemberIds;
	 * 
	 *//**
		 * 商户Id集合, 用户in查询
		 */

	/*
	 * private List<Long> merchantIds;
	 * 
	 *//**
		 * 店铺Id集合, 用户in查询
		 *//*
		 * private List<Long> shopIds;
		 */

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
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

	public Byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMerchantMemberId() {
		return merchantMemberId;
	}

	public void setMerchantMemberId(Long merchantMemberId) {
		this.merchantMemberId = merchantMemberId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getInnerTradeNo() {
		return innerTradeNo;
	}

	public void setInnerTradeNo(String innerTradeNo) {
		this.innerTradeNo = innerTradeNo;
	}

	public String getSnNum() {
		return snNum;
	}

	public void setSnNum(String snNum) {
		this.snNum = snNum;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCashierId() {
		return cashierId;
	}

	public void setCashierId(Long cashierId) {
		this.cashierId = cashierId;
	}

	public Byte getBizType() {
		return bizType;
	}

	public void setBizType(Byte bizType) {
		this.bizType = bizType;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}
