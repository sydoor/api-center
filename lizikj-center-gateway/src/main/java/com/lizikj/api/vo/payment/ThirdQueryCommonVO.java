package com.lizikj.api.vo.payment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2018/5/8 15:08
 */
@ApiModel
public class ThirdQueryCommonVO implements Serializable {

    private static final long serialVersionUID = 6046996488094166875L;

    @ApiModelProperty(value = "流水ID，不知道就不传")
    private Long payFlowId;

    @ApiModelProperty(value = "支付场景")
    private Byte sceneCode;

    @ApiModelProperty(value = "商户ID")
    protected Long merchantId;

    @ApiModelProperty(value = "店铺ID")
    protected Long shopId;

    @ApiModelProperty(value = "用户ID")
    protected Long userId;

    @ApiModelProperty(value = "收银员ID")
    protected Long cashierId;

    @ApiModelProperty(value = "内部交易号，对通道来说我们是外部交易号")
    private String innerTradeNo;

    @ApiModelProperty(value = "通道code：见：PaymentChannelEnum")
    private Byte channelCode;

    @ApiModelProperty(value = "支付方式：PaymentTypeEnum")
    private Byte paymentTypeCode;

    @ApiModelProperty(value = "内部退款号，对通道来说我们是外部退款号")
    private String innerRefundNo;

    @ApiModelProperty(value = "订单号：方便查找问题")
    private String orderNo;

    public String getInnerTradeNo() {
        return innerTradeNo;
    }

    public void setInnerTradeNo(String innerTradeNo) {
        this.innerTradeNo = innerTradeNo;
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

    public String getInnerRefundNo() {
        return innerRefundNo;
    }

    public void setInnerRefundNo(String innerRefundNo) {
        this.innerRefundNo = innerRefundNo;
    }

    public Long getPayFlowId() {
        return payFlowId;
    }

    public void setPayFlowId(Long payFlowId) {
        this.payFlowId = payFlowId;
    }

    public Byte getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(Byte sceneCode) {
        this.sceneCode = sceneCode;
    }

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
