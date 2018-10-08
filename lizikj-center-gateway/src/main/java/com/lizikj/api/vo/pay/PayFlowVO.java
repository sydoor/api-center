package com.lizikj.api.vo.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/9/22.
 */
@ApiModel
public class PayFlowVO {
     /** 支付流水ID
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
    @ApiModelProperty(value = "支付流水类型 1：正常支付 2：POS支付回调 默认1")
    private Byte payFlowType;
    /**
     * 支付通道代码
     */
    @ApiModelProperty(value = "支付通道代码:见PaymentChannelEnum")
    private Byte channelCode;
    /**
     * 支付方式代码
     */
    @ApiModelProperty(value = "支付方式代码 1 支付宝 2 微信 3 银联钱包 4 刷卡 5 现金 6 云闪付 8 会员 9 优惠券 10 美团券 12 预付券")
    private Byte paymentTypeCode;

    @ApiModelProperty(value = "支付方式代码")
    private String paymentTypeCodeName;
    /**
     * 支付场景代码
     */
    @ApiModelProperty(value = "支付场景代码：见PaymentSceneEnum")
    private Byte sceneCode;
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private Long amount;
    /**
     * 支付状态 0：未支付 1：支付成功 2：支付失败 默认0 </br>
     * {@link com.lizikj.payment.pay.enums.PayStatusEnum}
     */
    @ApiModelProperty(value = "支付状态 0：未支付 1：支付成功 2：支付失败 默认0")
    private Byte payStatus;
    /**
     * 是否能退款 0：否 1：是 默认1 </br>
     * {@link com.lizikj.payment.pay.enums.RefundEnableEnum}
     */
    @ApiModelProperty(value = "是否能退款 0：否 1：是 默认1 ")
    private Byte refundEnable;
    /**
     * 退款截止时间
     */
    @ApiModelProperty(value = "退款截止时间")
    private Date refundEndTime;
    /**
     * 退款状态 0：未退款 1：退款成功 2：退款失败 默认0 </br>
     * {@link com.lizikj.payment.refund.enums.RefundStatusEnum}
     */
    @ApiModelProperty(value = "退款状态 0：未退款 1：退款成功 2：退款失败 默认0")
    private Byte refundStatus;
    /**
     * 交易时间，第三方回调的时候插入
     */
    @ApiModelProperty(value = "交易时间，第三方回调的时候插入")
    private Date tradeTime;
    /**
     * 业务类型,1=点餐订单，2=快捷收银 </br>
     * {@link com.lizikj.payment.pay.enums.PayBizTypeEnum}
     */
    @ApiModelProperty(value = "业务类型,1=点餐订单，2=快捷收银")
    private Byte bizType;
    /**
     * 业务类型名称
     */
    @ApiModelProperty(value = "业务类型名称,1=点餐订单，2=快捷收银")
    private String bizTypeName;

    /**
     * 疑问流水的备注
     */
    @ApiModelProperty(value = "疑问流水的备注：存储json")
    private String questionOrderRemark;
    @ApiModelProperty(value = "流水备注")
    private String flowMemo;
    @ApiModelProperty(value = "外部收款方式")
    private String externalName;
    @ApiModelProperty(value = "外部收款方式ID")
    private Long externalId;

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

    public Byte getBizType() {
        return bizType;
    }

    public void setBizType(Byte bizType) {
        this.bizType = bizType;
    }

    public String getPaymentTypeCodeName() {
        return paymentTypeCodeName;
    }

    public void setPaymentTypeCodeName(String paymentTypeCodeName) {
        this.paymentTypeCodeName = paymentTypeCodeName;
    }

    public String getBizTypeName() {
        return bizTypeName;
    }

    public void setBizTypeName(String bizTypeName) {
        this.bizTypeName = bizTypeName;
    }

    public String getQuestionOrderRemark() {
        return questionOrderRemark;
    }

    public void setQuestionOrderRemark(String questionOrderRemark) {
        this.questionOrderRemark = questionOrderRemark;
    }

    public String getFlowMemo() {
        return flowMemo;
    }

    public void setFlowMemo(String flowMemo) {
        this.flowMemo = flowMemo;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }
}
