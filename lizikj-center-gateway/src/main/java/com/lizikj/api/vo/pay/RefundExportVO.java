package com.lizikj.api.vo.pay;

import java.util.Date;

public class RefundExportVO {
    private Long refundId;
    /**
     * 退款流水号
     */
    private String innerRefundNo;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 内部交易号
     */
    private String innerTradeNo;
    /**
     * 退款金额
     */
    private Double refundAmount;
    private String refundType;
    /**
     * 退款类型
     */
    private String refundAmountType;
    /**
     * 支付方式
     */
    private String paymentTypeCode;
    /**
     * 退款来源
     */
    private String refundSource;
    /**
     * 退款时间
     */
    private String refundTime;

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public String getInnerRefundNo() {
        return innerRefundNo;
    }

    public void setInnerRefundNo(String innerRefundNo) {
        this.innerRefundNo = innerRefundNo;
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

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getRefundAmountType() {
        return refundAmountType;
    }

    public void setRefundAmountType(String refundAmountType) {
        this.refundAmountType = refundAmountType;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public String getRefundSource() {
        return refundSource;
    }

    public void setRefundSource(String refundSource) {
        this.refundSource = refundSource;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }
}
