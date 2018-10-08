package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liangxiaolin on 2017/8/16.
 */
@ApiModel
public class OrderReportStatisticsVO {
    /**
     * 订单总额
     */
    @ApiModelProperty(value = "订单总额(分)")
    private Long totalAmount;
    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额（分）")
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
     * 毛利润
     */
    @ApiModelProperty(value = "毛利润")
    private Long profitAmount;
    /**
     * 退款jine
     */
    @ApiModelProperty(value = "退款jine")
    private Long refundAmount;
    /**
     * 订单数
     */
    @ApiModelProperty(value = "订单数")
    private Integer totalNum;
    /**
     * 有效订单数
     */
    @ApiModelProperty(value = "有效订单数")
    private Integer validNum;
    /**
     * 退款笔数
     */
    @ApiModelProperty(value = "退款笔数")
    private Integer refundNum;
    /**
     * 部分退款笔数
     */
    @ApiModelProperty(value = "部分退款笔数")
    private Integer partRefundNum;
    /**
     * 支付方式交易列表
     */
    @ApiModelProperty(value = "支付方式统计",name = "paymentReportVOS")
    private List<PaymentReportVO> paymentReportVOS;

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
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

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Long getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(Long profitAmount) {
        this.profitAmount = profitAmount;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getValidNum() {
        return validNum;
    }

    public void setValidNum(Integer validNum) {
        this.validNum = validNum;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Integer getPartRefundNum() {
        return partRefundNum;
    }

    public void setPartRefundNum(Integer partRefundNum) {
        this.partRefundNum = partRefundNum;
    }

    public List<PaymentReportVO> getPaymentReportVOS() {
        return paymentReportVOS;
    }

    public void setPaymentReportVOS(List<PaymentReportVO> paymentReportVOS) {
        this.paymentReportVOS = paymentReportVOS;
    }
}
