package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/8/28.
 */
@ApiModel
public class BusinessReportStatisticVO {
    /**
     * 会员总数
     */
    @ApiModelProperty(value = "会员总数")
    private Integer memberTotalNum;
    /**
     * 订单总额
     */
    @ApiModelProperty(value = "订单总额")
    private Long totalAmount;

    @ApiModelProperty(value = "目标营业额")
    private Long goalAmount;

    /**
     * 日均销售额
     */
    @ApiModelProperty(value = "日均销售额")
    private Long dailyAmount;

    @ApiModelProperty(value = "日均毛利润")
    private Long dailyProfit;

    /**
     * 日均销售笔数
     */
    @ApiModelProperty(value = "日均销售额")
    private Integer dailyNum;

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
     * 营销优惠金额
     */
    @ApiModelProperty(value = "营销优惠金额")
    private Long marketingBenefitAmount;
    /**
     * 会员优惠金额
     */
    @ApiModelProperty(value = "会员优惠金额")
    private Long memberBenefitAmount;
    /**
     * 其他优惠金额
     */
    @ApiModelProperty(value = "其他优惠金额")
    private Long otherBenefitAmount;

    /**
     * 毛利润
     */
    @ApiModelProperty(value = "毛利润")
    private Long profitAmount;
    /**
     * 退款jine
     */
    @ApiModelProperty(value = "退款")
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
     * 营销让利
     */
    @ApiModelProperty(value = "营销让利（分）")
    private Long marketAmount;
    /**
     * 营销让利笔数
     */
    @ApiModelProperty(value = "营销让利笔数")
    private Integer marketNum;
    /**
     * 会员让利
     */
    @ApiModelProperty(value = "会员让利（分）")
    private Long memberAmount;
    /**
     * 会员让利笔数
     */
    @ApiModelProperty(value = "会员让利笔数")
    private Integer memberNum;
    /**
     * 其他让利
     */
    @ApiModelProperty(value = "其他让利(分)")
    private Long otherAmount;
    /**
     * 其他让利笔数
     */
    @ApiModelProperty(value = "其他让利笔数")
    private Integer otherNum;
    /**
     * 昨日毛利润
     */
    @ApiModelProperty(value = "昨日毛利润")
    private Long yesterdayProfit;
    /**
     * 昨日订单总额
     */
    @ApiModelProperty(value = "昨日订单总额")
    private Long yesterdayAmount;
    /**
     * 毛利润占比
     */
    @ApiModelProperty(value = "毛利润占比")
    private Double profitRate;
    /**
     * 优惠占比
     */
    @ApiModelProperty(value = "优惠占比")
    private Double benefitRate;
    /**
     * 昨日有效订单总额
     */
    @ApiModelProperty(value = "昨日有效订单总额")
    private Integer yesterdayValidNum;
    /**
     * 昨日会员总数
     */
    @ApiModelProperty(value = "昨日会员总数")
    private Integer yesterdayMemberTotalNum;

    public Integer getMemberTotalNum() {
        return memberTotalNum;
    }

    public void setMemberTotalNum(Integer memberTotalNum) {
        this.memberTotalNum = memberTotalNum;
    }

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

    public Long getDailyAmount() {
        return dailyAmount;
    }

    public void setDailyAmount(Long dailyAmount) {
        this.dailyAmount = dailyAmount;
    }

    public Integer getDailyNum() {
        return dailyNum;
    }

    public void setDailyNum(Integer dailyNum) {
        this.dailyNum = dailyNum;
    }

    public Long getMarketingBenefitAmount() {
        return marketingBenefitAmount;
    }

    public void setMarketingBenefitAmount(Long marketingBenefitAmount) {
        this.marketingBenefitAmount = marketingBenefitAmount;
    }

    public Long getMemberBenefitAmount() {
        return memberBenefitAmount;
    }

    public void setMemberBenefitAmount(Long memberBenefitAmount) {
        this.memberBenefitAmount = memberBenefitAmount;
    }

    public Long getOtherBenefitAmount() {
        return otherBenefitAmount;
    }

    public void setOtherBenefitAmount(Long otherBenefitAmount) {
        this.otherBenefitAmount = otherBenefitAmount;
    }

    public Long getMarketAmount() {
        return marketAmount;
    }

    public void setMarketAmount(Long marketAmount) {
        this.marketAmount = marketAmount;
    }

    public Integer getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(Integer marketNum) {
        this.marketNum = marketNum;
    }

    public Long getMemberAmount() {
        return memberAmount;
    }

    public void setMemberAmount(Long memberAmount) {
        this.memberAmount = memberAmount;
    }

    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public Long getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(Long otherAmount) {
        this.otherAmount = otherAmount;
    }

    public Integer getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(Integer otherNum) {
        this.otherNum = otherNum;
    }

    public Long getYesterdayProfit() {
        return yesterdayProfit;
    }

    public void setYesterdayProfit(Long yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }

    public Long getYesterdayAmount() {
        return yesterdayAmount;
    }

    public void setYesterdayAmount(Long yesterdayAmount) {
        this.yesterdayAmount = yesterdayAmount;
    }

    public Double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(Double profitRate) {
        this.profitRate = profitRate;
    }

    public Double getBenefitRate() {
        return benefitRate;
    }

    public void setBenefitRate(Double benefitRate) {
        this.benefitRate = benefitRate;
    }

    public Integer getYesterdayValidNum() {
        return yesterdayValidNum;
    }

    public void setYesterdayValidNum(Integer yesterdayValidNum) {
        this.yesterdayValidNum = yesterdayValidNum;
    }

    public Integer getYesterdayMemberTotalNum() {
        return yesterdayMemberTotalNum;
    }

    public void setYesterdayMemberTotalNum(Integer yesterdayMemberTotalNum) {
        this.yesterdayMemberTotalNum = yesterdayMemberTotalNum;
    }

    public Long getDailyProfit() {
        return dailyProfit;
    }

    public void setDailyProfit(Long dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    public Long getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Long goalAmount) {
        this.goalAmount = goalAmount;
    }
}
