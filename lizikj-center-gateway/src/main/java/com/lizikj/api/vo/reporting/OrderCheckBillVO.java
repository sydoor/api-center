package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @Date 2018/1/16 16:50
 */
@ApiModel
public class OrderCheckBillVO {
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "打印序号")
    private Long printSeq;
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
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "收银信息")
    private CashierVO cashier;

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

    public Long getPrintSeq() {
        return printSeq;
    }

    public void setPrintSeq(Long printSeq) {
        this.printSeq = printSeq;
    }

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

	public CashierVO getCashier() {
		return cashier;
	}

	public void setCashier(CashierVO cashier) {
		this.cashier = cashier;
	}
	public List<PaymentReportVO> getPayAndRefundReports() {
		return payAndRefundReports;
	}
	public void setPayAndRefundReports(List<PaymentReportVO> payAndRefundReports) {
		this.payAndRefundReports = payAndRefundReports;
	}
}
