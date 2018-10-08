package com.lizikj.api.vo.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/9/22.
 */
@ApiModel
public class OrderAndPayVO {
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

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
    private Date tradeTime;
    /**
     * 业务类型,1=点餐订单，2=快捷收银 </br>
     * {@link com.lizikj.payment.pay.enums.PayBizTypeEnum}
     */
    @ApiModelProperty(value = "业务类型,1=点餐订单，2=快捷收银")
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
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private Long benefitAmount;
    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private Long payAmount;
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
    @ApiModelProperty(value = "流水备注")
    private String flowMemo;
    @ApiModelProperty(value = "外部收款方式")
    private String externalName;
    @ApiModelProperty(value = "外部收款方式ID")
    private Long externalId;

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

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
