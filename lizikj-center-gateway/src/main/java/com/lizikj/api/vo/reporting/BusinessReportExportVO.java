package com.lizikj.api.vo.reporting;

import com.lizikj.reporting.dto.PaymentReportExportDTO;
import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

/**
 * Created by liangxiaolin on 2017/9/20.
 */

public class BusinessReportExportVO {
    /**
     * 统计日期
     */
    private Date reportDate;

    private String reportDateStr;
    /**
     * 商户ID
     */
    private Long merchantId;
    /**
     * 商户名称
     */
    private String merchantName;
    /**
     * 店铺ID
     */
    private Long shopId;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 会员总数
     */
    private Integer memberTotalNum;

    /**
     * 订单总额
     */
    private Double totalAmount;
    /**
     /**
     * 实收金额
     */
    private Double receivedAmount;
    /**
     * 成本
     */
    private Double costAmount;
    /**
     * 优惠金额
     */
    private Double benefitAmount;

    private Double goalAmount;

    /**
     * 毛利润
     */
    private Double profitAmount;
    /**
     * 毛利润率
     */
    private Double profitRate;
    /**
     * 退款jine
     */
    private Double refundAmount;
    /**
     * 订单数
     */
    private Integer totalNum;
    /**
     * 有效订单数
     */
    private Integer validNum;
    /**
     * 退款笔数
     */
    private Integer refundNum;

    private String merchandiseTopSale;
    /**
     * 现金金额
     */
    private Double cashAmount;
    /**
     * 现金笔数
     */
    private Integer cashNums;
    /**
     * 支付宝金额
     */
    private Double aliAmount;
    /**
     * 支付宝笔数
     */
    private Integer aliNums;
    /**
     * 微信金额
     */
    private Double webchatAmount;
    /**
     * 微信笔数
     */
    private Integer webchatNums;
    /**
     * 刷卡金额
     */
    private Double cardAmount;
    /**
     * 刷卡笔数
     */
    private Integer cardNums;
    /**
     * 银联金额
     */
    private Double unionPayAmount;
    /**
     * 银联笔数
     */
    private Integer unionPayNums;
    /**
     * 会员余额支付金额
     */
    private Double memberPayAmount;
    /**
     * 会员余额支付笔数
     */
    private Integer memberPayNums;
    /**
     * 美团代金券支付金额
     */
    private Double meituanPayAmount;
    /**
     * 美团代金券支付笔数
     */
    private Integer meituanPayNums;

    public BusinessReportExportVO() {
        this.memberTotalNum = 0;
        this.totalAmount = 0.00D;
        this.receivedAmount = 0.00D;
        this.costAmount = 0.00D;
        this.benefitAmount = 0.00D;
        this.profitAmount = 0.00D;
        this.profitRate = 0.00D;
        this.refundAmount = 0.00D;
        this.totalNum = 0;
        this.validNum = 0;
        this.refundNum = 0;
        this.merchandiseTopSale = "";
        this.cashAmount = 0.00D;
        this.cashNums = 0;
        this.aliAmount = 0.00D;
        this.aliNums = 0;
        this.webchatAmount = 0.00D;
        this.webchatNums = 0;
        this.cardAmount = 0.00D;
        this.cardNums = 0;
        this.unionPayAmount = 0.00D;
        this.unionPayNums = 0;
        this.meituanPayAmount = 0.00D;
        this.meituanPayNums = 0;
        this.memberPayAmount = 0.00D;
        this.memberPayNums = 0;
        this.goalAmount = 0.00D;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDateStr() {
        return reportDateStr;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
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

    public Integer getMemberTotalNum() {
        return memberTotalNum;
    }

    public void setMemberTotalNum(Integer memberTotalNum) {
        this.memberTotalNum = memberTotalNum;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Double getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Double costAmount) {
        this.costAmount = costAmount;
    }

    public Double getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Double benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Double getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(Double profitAmount) {
        this.profitAmount = profitAmount;
    }

    public Double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(Double profitRate) {
        this.profitRate = profitRate;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
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

    public String getMerchandiseTopSale() {
        return merchandiseTopSale;
    }

    public void setMerchandiseTopSale(String merchandiseTopSale) {
        this.merchandiseTopSale = merchandiseTopSale;
    }

    public Double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getCashNums() {
        return cashNums;
    }

    public void setCashNums(Integer cashNums) {
        this.cashNums = cashNums;
    }

    public Double getAliAmount() {
        return aliAmount;
    }

    public void setAliAmount(Double aliAmount) {
        this.aliAmount = aliAmount;
    }

    public Integer getAliNums() {
        return aliNums;
    }

    public void setAliNums(Integer aliNums) {
        this.aliNums = aliNums;
    }

    public Double getWebchatAmount() {
        return webchatAmount;
    }

    public void setWebchatAmount(Double webchatAmount) {
        this.webchatAmount = webchatAmount;
    }

    public Integer getWebchatNums() {
        return webchatNums;
    }

    public void setWebchatNums(Integer webchatNums) {
        this.webchatNums = webchatNums;
    }

    public Double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(Double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public Integer getCardNums() {
        return cardNums;
    }

    public void setCardNums(Integer cardNums) {
        this.cardNums = cardNums;
    }

    public Double getUnionPayAmount() {
        return unionPayAmount;
    }

    public void setUnionPayAmount(Double unionPayAmount) {
        this.unionPayAmount = unionPayAmount;
    }

    public Integer getUnionPayNums() {
        return unionPayNums;
    }

    public void setUnionPayNums(Integer unionPayNums) {
        this.unionPayNums = unionPayNums;
    }

    public Double getMemberPayAmount() {
        return memberPayAmount;
    }

    public void setMemberPayAmount(Double memberPayAmount) {
        this.memberPayAmount = memberPayAmount;
    }

    public Integer getMemberPayNums() {
        return memberPayNums;
    }

    public void setMemberPayNums(Integer memberPayNums) {
        this.memberPayNums = memberPayNums;
    }

    public Double getMeituanPayAmount() {
        return meituanPayAmount;
    }

    public void setMeituanPayAmount(Double meituanPayAmount) {
        this.meituanPayAmount = meituanPayAmount;
    }

    public Integer getMeituanPayNums() {
        return meituanPayNums;
    }

    public void setMeituanPayNums(Integer meituanPayNums) {
        this.meituanPayNums = meituanPayNums;
    }

    public Double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Double goalAmount) {
        this.goalAmount = goalAmount;
    }
}
