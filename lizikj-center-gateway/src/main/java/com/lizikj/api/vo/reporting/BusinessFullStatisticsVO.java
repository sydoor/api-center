package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author lxl
 * @Date 2018/8/7 19:11
 */
@ApiModel(value = "经营全领域统计")
public class BusinessFullStatisticsVO {
    @ApiModelProperty(value = "营业额")
    private Long totalAmount = 0L;
    @ApiModelProperty(value = "营业订单数")
    private Integer totalNum = Integer.valueOf(0);
    @ApiModelProperty(value = "就餐人数")
    private Integer numOfGuests = Integer.valueOf(0);
    @ApiModelProperty(value = "人均消费")
    private Long avgAmountOfGuests = 0L;
    @ApiModelProperty(value = "人均消费笔数")
    private Integer avgNumOfGuests = Integer.valueOf(0);
    @ApiModelProperty(value = "实际收入")
    List<PaymentReportVO> realPayReports;
    @ApiModelProperty(value = "其他收入")
    List<PaymentReportVO> couponPayReports;
    @ApiModelProperty(value = "收款和退款列表")
    List<PaymentReportVO> payAndRefundReports;
    @ApiModelProperty(value = "优惠")
    List<OrderBenefitReportVO> benefitReports;
    @ApiModelProperty(value = "退款和取消订单")
    List<OrderSumReportVO> orderSumReports;
    @ApiModelProperty(value = "菜品统计")
    List<CategoryProduceReportVO> categoryProduceReportVOS;
    @ApiModelProperty(value = "收银员收款统计")
    List<CashierPaymentReportVO> cashierPaymentReportVOS;
    @ApiModelProperty(value = "会员和流水统计")
    MemberAndFlowReportVO memberAndFlowReportVO;


    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getNumOfGuests() {
        return numOfGuests;
    }

    public void setNumOfGuests(Integer numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    public Long getAvgAmountOfGuests() {
        return avgAmountOfGuests;
    }

    public void setAvgAmountOfGuests(Long avgAmountOfGuests) {
        this.avgAmountOfGuests = avgAmountOfGuests;
    }

    public Integer getAvgNumOfGuests() {
        return avgNumOfGuests;
    }

    public void setAvgNumOfGuests(Integer avgNumOfGuests) {
        this.avgNumOfGuests = avgNumOfGuests;
    }

    public List<PaymentReportVO> getRealPayReports() {
        return realPayReports;
    }

    public void setRealPayReports(List<PaymentReportVO> realPayReports) {
        this.realPayReports = realPayReports;
    }

    public List<PaymentReportVO> getCouponPayReports() {
        return couponPayReports;
    }

    public void setCouponPayReports(List<PaymentReportVO> couponPayReports) {
        this.couponPayReports = couponPayReports;
    }

    public List<PaymentReportVO> getPayAndRefundReports() {
        return payAndRefundReports;
    }

    public void setPayAndRefundReports(List<PaymentReportVO> payAndRefundReports) {
        this.payAndRefundReports = payAndRefundReports;
    }

    public List<OrderBenefitReportVO> getBenefitReports() {
        return benefitReports;
    }

    public void setBenefitReports(List<OrderBenefitReportVO> benefitReports) {
        this.benefitReports = benefitReports;
    }

    public List<OrderSumReportVO> getOrderSumReports() {
        return orderSumReports;
    }

    public void setOrderSumReports(List<OrderSumReportVO> orderSumReports) {
        this.orderSumReports = orderSumReports;
    }

    public List<CategoryProduceReportVO> getCategoryProduceReportVOS() {
        return categoryProduceReportVOS;
    }

    public void setCategoryProduceReportVOS(List<CategoryProduceReportVO> categoryProduceReportVOS) {
        this.categoryProduceReportVOS = categoryProduceReportVOS;
    }

    public List<CashierPaymentReportVO> getCashierPaymentReportVOS() {
        return cashierPaymentReportVOS;
    }

    public void setCashierPaymentReportVOS(List<CashierPaymentReportVO> cashierPaymentReportVOS) {
        this.cashierPaymentReportVOS = cashierPaymentReportVOS;
    }

    public MemberAndFlowReportVO getMemberAndFlowReportVO() {
        return memberAndFlowReportVO;
    }

    public void setMemberAndFlowReportVO(MemberAndFlowReportVO memberAndFlowReportVO) {
        this.memberAndFlowReportVO = memberAndFlowReportVO;
    }
}
