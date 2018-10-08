package com.lizikj.api.vo.reporting;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liangxiaolin on 2017/9/29.
 */
@ApiModel
public class ValueAddedServiceVO {
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    @ApiModelProperty(value = "代理商ID")
    private Long agentId;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "增值服务ID")
    private Long agentChargeModeRuleId;
    @ApiModelProperty(value = "增值服务类型")
    private BigDecimal agentChargeModeRuleType;
    @ApiModelProperty(value = "增值服务名称")
    private String agentChargeModeRuleName;
    @ApiModelProperty(value = "支付方式编码")
    private Byte paymentTypeCode;
    @ApiModelProperty(value = "支付方式名称")
    private String paymentTypeName;
    @ApiModelProperty(value = "业务类型 1开通 ")
    private Integer renewStatus;
    @ApiModelProperty(value = "支付日期")
    private Date paymentDate;
    @ApiModelProperty(value = "到期时间")
    private Date expiredTime;
    @ApiModelProperty(value = "剩余天数")
    private Integer daysRemaining;
    @ApiModelProperty(value = "基本费用")
    private Long baseAmount;
    @ApiModelProperty(value = "销售费用")
    private Long saleAmount;

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

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
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

    public Long getAgentChargeModeRuleId() {
        return agentChargeModeRuleId;
    }

    public void setAgentChargeModeRuleId(Long agentChargeModeRuleId) {
        this.agentChargeModeRuleId = agentChargeModeRuleId;
    }

    public BigDecimal getAgentChargeModeRuleType() {
        return agentChargeModeRuleType;
    }

    public void setAgentChargeModeRuleType(BigDecimal agentChargeModeRuleType) {
        this.agentChargeModeRuleType = agentChargeModeRuleType;
    }

    public String getAgentChargeModeRuleName() {
        return agentChargeModeRuleName;
    }

    public void setAgentChargeModeRuleName(String agentChargeModeRuleName) {
        this.agentChargeModeRuleName = agentChargeModeRuleName;
    }

    public Byte getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(Byte paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public Integer getRenewStatus() {
        return renewStatus;
    }

    public void setRenewStatus(Integer renewStatus) {
        this.renewStatus = renewStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(Integer daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public Long getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Long baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Long getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Long saleAmount) {
        this.saleAmount = saleAmount;
    }
}
