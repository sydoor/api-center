package com.lizikj.api.vo.payment;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PayFlowVO {
	/**
	 * 支付流水ID
	 */
	@ApiModelProperty(value = "支付流水ID")
	private Long id;

	/**
	 * 订单号
	 */
	@ApiModelProperty(value = "订单号")
	private String orderNo;

	/**
	 * 内部交易号
	 */
	@ApiModelProperty(value = "内部交易号")
	private String innerTradeNo;

	/**
	 * 外部交易号
	 */
	@ApiModelProperty(value = "外部交易号")
	private String outTradeNo;

	/**
	 * 支付流水类型 1：正常支付 2：POS支付回调 默认1 </br>
	 * {@link com.lizikj.payment.pay.enums.PayFlowTypeEnum}
	 */
	@ApiModelProperty(value = "支付流水类型")
	private Byte payFlowType;

	/**
	 * 支付通道代码
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
	 * 金额
	 */
	@ApiModelProperty(value = "金额")
	private Long amount;

	/**
	 * 支付状态 0：未支付 1：支付成功 2：支付失败 默认0
	 */
	@ApiModelProperty(value = "支付状态")
	private Byte payStatus;

	/**
	 * 是否能退款 0：否 1：是 默认1
	 */
	@ApiModelProperty(value = "是否能退款 0：否 1：是 默认1")
	private Byte refundEnable;

	/**
	 * 退款截止时间
	 */
	@ApiModelProperty(value = "退款截止时间")
	private Date refundEndTime;

	/**
	 * 退款状态 0：未退款 1：退款成功 2：退款失败 默认0
	 */
	@ApiModelProperty(value = "退款状态 0：未退款 1：退款成功 2：退款失败 默认0")
	private Byte refundStatus;

	/**
	 * 交易时间，第三方回调的时候插入
	 */
	@ApiModelProperty(value = "交易时间")
	private Date tradeTime;

	/**
	 * 业务类型,1=点餐订单，2=快捷收银 </br>
	 */
	@ApiModelProperty(value = "业务类型,1=点餐订单，2=快捷收银, 3=会员充值，4=增值服务，5=股东会员充值")
	private Byte orderBizType;

	/**
	 * 版本号，如果一个交易多次支付，则根据版本号区分最新支付流水
	 */
	@ApiModelProperty(value = "支付版本号")
	private Long payVersion;

	/**
	 * 自动去第三方查询支付结果的次数
	 */
	@ApiModelProperty(value = "第三方查询支付结果的次数")
	private Integer queryCount;

	/**
	 * 通知状态：支付回调，订单处理完成后支付网关，1=成功，0=未通知
	 */
	@ApiModelProperty(value = "通知状态：支付回调，订单处理完成后支付网关，1=成功，0=未通知")
	private Byte noticeStatus;

	/**
	 * 记录回调计划任务加载次数，加载超过一定次数后，需要人工处理此数据
	 */
	@ApiModelProperty(value = "回调计划任务加载次数")
	private Integer orderCallbackTimes;

	/**
	 * 删除状态 1删除，0存在
	 */
	@ApiModelProperty(value = "删除状态 1删除，0存在")
	private Byte removeStatus;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	/**
	 * 支付流水详情
	 */
	@ApiModelProperty(value = "支付流水详情")
	private PayFlowDetaiResultVO accountDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Byte getPayFlowType() {
		return payFlowType;
	}

	public void setPayFlowType(Byte payFlowType) {
		this.payFlowType = payFlowType;
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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}

	public Byte getRefundEnable() {
		return refundEnable;
	}

	public void setRefundEnable(Byte refundEnable) {
		this.refundEnable = refundEnable;
	}

	public Date getRefundEndTime() {
		return refundEndTime;
	}

	public void setRefundEndTime(Date refundEndTime) {
		this.refundEndTime = refundEndTime;
	}

	public Byte getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Byte refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Byte getOrderBizType() {
		return orderBizType;
	}

	public void setOrderBizType(Byte orderBizType) {
		this.orderBizType = orderBizType;
	}

	public Long getPayVersion() {
		return payVersion;
	}

	public void setPayVersion(Long payVersion) {
		this.payVersion = payVersion;
	}

	public Integer getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(Integer queryCount) {
		this.queryCount = queryCount;
	}

	public Byte getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(Byte noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public Integer getOrderCallbackTimes() {
		return orderCallbackTimes;
	}

	public void setOrderCallbackTimes(Integer orderCallbackTimes) {
		this.orderCallbackTimes = orderCallbackTimes;
	}

	public Byte getRemoveStatus() {
		return removeStatus;
	}

	public void setRemoveStatus(Byte removeStatus) {
		this.removeStatus = removeStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public PayFlowDetaiResultVO getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(PayFlowDetaiResultVO accountDetail) {
		this.accountDetail = accountDetail;
	}

}