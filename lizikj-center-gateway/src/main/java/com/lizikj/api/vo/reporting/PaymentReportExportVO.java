package com.lizikj.api.vo.reporting;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/9/20.
 */
public class PaymentReportExportVO {
    private String payName; //支付方式名称
    private Byte paymentTypeCode;//支付方式 paymentTypeCodeEnum.java中的值

    private Date reportDate;//统计日期

    private Long payAmount;//支付金额

    private Long refundAmount;//退款金额

    private Integer payNums; //交易笔数

    private Integer refundNums;//退款笔数

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public Byte getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(Byte paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getPayNums() {
        return payNums;
    }

    public void setPayNums(Integer payNums) {
        this.payNums = payNums;
    }

    public Integer getRefundNums() {
        return refundNums;
    }

    public void setRefundNums(Integer refundNums) {
        this.refundNums = refundNums;
    }

}
