package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/8/9.
 */
@ApiModel
public class PaymentReportVO {
    @ApiModelProperty(value = "代理商名称")
    private String agentName;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    /**
     * 支付方式名称
     */
    @ApiModelProperty(value = "支付方式名称")
    private String payName;
    /**
     * 统计日期
     */
    @ApiModelProperty(value = "统计日期")
    private Date reportDate;
    @ApiModelProperty(value = "统计日期字符串形式")
    private String reportTime;
    @ApiModelProperty(value = "商户id")
    private Long merchantId;//商户id
    @ApiModelProperty(value = "店铺id")
    private Long shopId;//店铺id
    @ApiModelProperty(value = "支付方式 1 支付宝 2 微信 3 银联钱包 4 刷卡 5 现金 6 云闪付 8 会员 9 优惠券 10 美团券 12 预付券")
    private Byte paymentTypeCode;//支付方式 PayTypeEnum.java中的值
    @ApiModelProperty(value = "统计ID")
    private Date startDate;//统计开始日期
    @ApiModelProperty(value = "支付金额（分）")
    private Long  payAmount;//支付金额
    @ApiModelProperty(value = "退款金额(分)")
    private Long  refundAmount;//退款金额
    @ApiModelProperty(value = "交易笔数")
    private Integer payNums; //交易笔数
    @ApiModelProperty(value = "退款笔数")
    private Integer refundNums;//退款笔数
    @ApiModelProperty(value = "退款订单数")
    private Integer refundOrderNums;
    @ApiModelProperty(value = "支付订单数")
    private Integer payOrderNums;
    @ApiModelProperty(value = "占堂食比例（%）")
    private Double payRateInShop;
    @ApiModelProperty(value = "外部收款方式")
    private String externalName;
    @ApiModelProperty(value = "外部收款方式ID")
    private Long externalId;

    public String getMerchantName() {
        return merchantName;
    }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getPayName() {
        return payName;
    }
    public void setPayName(String payName) {
        this.payName = payName;
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
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public Byte getPaymentTypeCode() {
        return paymentTypeCode;
    }
    public void setPaymentTypeCode(Byte paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
    public String getAgentName() {
        return agentName;
    }
    public String getReportTime() {
        return reportTime;
    }
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getRefundOrderNums() {
        return refundOrderNums;
    }

    public void setRefundOrderNums(Integer refundOrderNums) {
        this.refundOrderNums = refundOrderNums;
    }

    public Integer getPayOrderNums() {
        return payOrderNums;
    }

    public void setPayOrderNums(Integer payOrderNums) {
        this.payOrderNums = payOrderNums;
    }

    public Double getPayRateInShop() {
        return payRateInShop;
    }

    public void setPayRateInShop(Double payRateInShop) {
        this.payRateInShop = payRateInShop;
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
