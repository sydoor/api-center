package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/8/7 17:39
 */
@ApiModel(value = "会员和流水统计")
public class MemberAndFlowReportVO {
    @ApiModelProperty(value = "统计日期字符串")
    private String reportDateStr;
    @ApiModelProperty(value = "统计日期")
    private Date reportDate;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    @ApiModelProperty(value = "累计充值金额")
    private Long totalRechargeAmount;
    @ApiModelProperty(value = "累计会员支付总额")
    private Long totalCostRechargeAmount;
    @ApiModelProperty(value = "退款总额")
    private Long totalRefundAmount;
    @ApiModelProperty(value = "冻结总额")
    private Long totalFrozenAmount;
    @ApiModelProperty(value = "会员支付消费笔数")
    private Integer orderNum;
    @ApiModelProperty(value = "充值笔数")
    private Integer rechargeNum;
    @ApiModelProperty(value = "退款笔数")
    private Integer refundNum;
    @ApiModelProperty(value = "冻结笔数")
    private Integer frozenNum;
    @ApiModelProperty(value = "会员总数")
    private Integer totalMemberNum;
    @ApiModelProperty(value = "会员新增数")
    private Integer increaseMemberNum;
    @ApiModelProperty(value = "会员余额")
    private Long balanceAmount;

    public Long getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Long balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getReportDateStr() {
        return reportDateStr;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
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

    public Long getTotalRechargeAmount() {
        return totalRechargeAmount;
    }

    public void setTotalRechargeAmount(Long totalRechargeAmount) {
        this.totalRechargeAmount = totalRechargeAmount;
    }

    public Long getTotalCostRechargeAmount() {
        return totalCostRechargeAmount;
    }

    public void setTotalCostRechargeAmount(Long totalCostRechargeAmount) {
        this.totalCostRechargeAmount = totalCostRechargeAmount;
    }

    public Long getTotalRefundAmount() {
        return totalRefundAmount;
    }

    public void setTotalRefundAmount(Long totalRefundAmount) {
        this.totalRefundAmount = totalRefundAmount;
    }

    public Long getTotalFrozenAmount() {
        return totalFrozenAmount;
    }

    public void setTotalFrozenAmount(Long totalFrozenAmount) {
        this.totalFrozenAmount = totalFrozenAmount;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getRechargeNum() {
        return rechargeNum;
    }

    public void setRechargeNum(Integer rechargeNum) {
        this.rechargeNum = rechargeNum;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Integer getFrozenNum() {
        return frozenNum;
    }

    public void setFrozenNum(Integer frozenNum) {
        this.frozenNum = frozenNum;
    }

    public Integer getTotalMemberNum() {
        return totalMemberNum;
    }

    public void setTotalMemberNum(Integer totalMemberNum) {
        this.totalMemberNum = totalMemberNum;
    }

    public Integer getIncreaseMemberNum() {
        return increaseMemberNum;
    }

    public void setIncreaseMemberNum(Integer increaseMemberNum) {
        this.increaseMemberNum = increaseMemberNum;
    }
}
