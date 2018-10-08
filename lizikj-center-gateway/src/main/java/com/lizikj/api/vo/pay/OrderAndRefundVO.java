package com.lizikj.api.vo.pay;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/9/23.
 */
public class OrderAndRefundVO {
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;
    /**
     * 退款流水号
     */
    @ApiModelProperty(value = "退款流水号")
    private String innerRefundNo;

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
     * 支付通道代码
     */
    @ApiModelProperty(value = "支付通道代码")
    private Byte channelCode;
    /**
     * 支付方式代码
     */
    @ApiModelProperty(value = "支付方式代码 1 支付宝 2 微信 3 银联钱包 4 刷卡 5 现金 6 云闪付 8 会员 9 优惠券 10 美团券 12 预付券")
    private Byte paymentTypeCode;
    /**
     * 支付场景代码
     */
    @ApiModelProperty(value = "支付场景代码")
    private Byte sceneCode;
    /**
     * 交易时间，第三方回调的时候插入
     */
    @ApiModelProperty(value = "交易时间，第三方回调的时候插入")
    private Date refundTime;
    /**
     * 业务类型,1=点餐订单，2=快捷收银 </br>
     * {@link com.lizikj.order.enums.OrderBizTypeEnum}
     */
    @ApiModelProperty(value = "业务类型,1=点餐订单，2=快捷收银，3=会员充值，4=增值服务")
    private Byte bizType;
    /**
     * 会员手机号
     */
    @ApiModelProperty(value = "会员手机号")
    private String mobile;
    /**
     * snNum号
     */
    @ApiModelProperty(value = "snNum号")
    private String snNum;
    /**
     * 订单总金额
     */
    @ApiModelProperty(value = "订单总金额")
    private Long totalAmount;
    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额")
    private Long receivedAmount;
    /**
     * 成本
     */
    @ApiModelProperty(value = "成本")
    private Long costAmount;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private Long benefitAmount;
    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    private Long refundAmount;
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "店铺名称")
    private Long merchantId;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 退款类型
     */
    @ApiModelProperty(value = "退款类型0=原路退款，1=现金退款")
    private Byte refundType;
    /**
     * 退款模式
     */
    @ApiModelProperty(value = "退款金额模式，1=全额退款，2=部分退款")
    private Byte refundAmountType;
    @ApiModelProperty(value = "流水备注")
    private String flowMemo;
    @ApiModelProperty(value = "外部收款方式")
    private String externalName;
    @ApiModelProperty(value = "外部收款方式ID")
    private Long externalId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Byte getBizType() {
        return bizType;
    }

    public void setBizType(Byte bizType) {
        this.bizType = bizType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSnNum() {
        return snNum;
    }

    public void setSnNum(String snNum) {
        this.snNum = snNum;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Byte getRefundType() {
        return refundType;
    }

    public void setRefundType(Byte refundType) {
        this.refundType = refundType;
    }

    public Byte getRefundAmountType() {
        return refundAmountType;
    }

    public void setRefundAmountType(Byte refundAmountType) {
        this.refundAmountType = refundAmountType;
    }

    public Long getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Long receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Long getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Long costAmount) {
        this.costAmount = costAmount;
    }

    public String getInnerRefundNo() {
        return innerRefundNo;
    }

    public void setInnerRefundNo(String innerRefundNo) {
        this.innerRefundNo = innerRefundNo;
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
