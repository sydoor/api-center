package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2017/12/1 16:29
 */
@ApiModel
public class MerchantMemberFlowStatisticVO {
    /**
     * 统计日期
     */
    @ApiModelProperty(value = "统计日期")
    private String reportDateStr;
    /**
     * 商户会员ID
     */
    @ApiModelProperty(value = "商户会员ID")
    private Long merchantMemberId;
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 新增会员数
     */
    @ApiModelProperty(value = "新增会员数")
    private Integer memberIncreaseNum;
    /**
     * 充值次数
     */
    @ApiModelProperty(value = "充值次数")
    private Integer rechargeNum;
    /**
     * 充值金额
     */
    @ApiModelProperty(value = "充值金额")
    private Long rechargeAmount;

    public String getReportDateStr() {
        return reportDateStr;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMemberIncreaseNum() {
        return memberIncreaseNum;
    }

    public void setMemberIncreaseNum(Integer memberIncreaseNum) {
        this.memberIncreaseNum = memberIncreaseNum;
    }

    public Integer getRechargeNum() {
        return rechargeNum;
    }

    public void setRechargeNum(Integer rechargeNum) {
        this.rechargeNum = rechargeNum;
    }

    public Long getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Long rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }
}
