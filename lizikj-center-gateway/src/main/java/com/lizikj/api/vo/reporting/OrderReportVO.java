package com.lizikj.api.vo.reporting;

import com.lizikj.reporting.dto.ShopOrderReportDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/8/29.
 */
@ApiModel
public class OrderReportVO {
    @ApiModelProperty(value = "统计日期，小时格式：yyyy-mm-dd hh:00:00 ")
    private Date reportDate;
    @ApiModelProperty(value = "统计时间")
    private Long reportTime;
    @ApiModelProperty(value = "统计时间字符串格式")
    private String reportDateStr;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    /**
     * 订单总额
     */
    @ApiModelProperty(value = "订单总额")
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
    @ApiModelProperty(value = "商户ID")
    private Long benefitAmount;
    /**
     * 毛利润
     */
    @ApiModelProperty(value = "毛利润")
    private Long profitAmount;
    /**
     * 退款
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

    public OrderReportVO() {
        this.totalAmount = 0L;
        this.receivedAmount = 0L;
        this.costAmount = 0L;
        this.benefitAmount = 0L;
        this.profitAmount = 0L;
        this.refundAmount = 0L;
        this.totalNum = 0;
        this.validNum = 0;
        this.refundNum = 0;
        this.partRefundNum = 0;
    }

    public void add(ShopOrderReportDTO dto){
        this.totalAmount = this.totalAmount + dto.getTotalAmount();
        this.receivedAmount = this.benefitAmount + dto.getBenefitAmount();
        this.costAmount = this.costAmount + dto.getCostAmount();
        this.benefitAmount = this.benefitAmount + dto.getBenefitAmount();
        this.profitAmount = this.profitAmount + dto.getProfitAmount();
        this.refundAmount = this.refundAmount + dto.getRefundAmount();
        this.totalNum = this.totalNum + dto.getTotalNum();
        this.validNum = this.validNum + dto.getValidNum();
        this.refundNum = this.refundNum + dto.getRefundNum();
        this.partRefundNum = this.partRefundNum + dto.getPartRefundNum();
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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

    public Long getReportTime() {
        return reportTime;
    }
    public void setReportTime(Long reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportDateStr() {
        return reportDateStr;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
    }
}
