package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/9/13.
 */
@ApiModel
public class MemberConsumeReportVO {
    @ApiModelProperty(value = "会员ID")
    private Long memberId;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    /**
     * 注册商户数
     */
    @ApiModelProperty(value = "注册商户数")
    private Integer registerMerchantNum;
    @ApiModelProperty(value = "消费次数")
    private Integer consumeNum;
    @ApiModelProperty(value = "退款次数")
    private Integer refundNum;
    @ApiModelProperty(value = "优惠笔数")
    private Integer benefitNum;
    @ApiModelProperty(value = "消费总额")
    private Long consumeAmount;
    @ApiModelProperty(value = "退款金额")
    private Long refundAmount;
    @ApiModelProperty(value = "优惠金额")
    private Long benefitAmount;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Integer getRegisterMerchantNum() {
        return registerMerchantNum;
    }

    public void setRegisterMerchantNum(Integer registerMerchantNum) {
        this.registerMerchantNum = registerMerchantNum;
    }

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Integer getBenefitNum() {
        return benefitNum;
    }

    public void setBenefitNum(Integer benefitNum) {
        this.benefitNum = benefitNum;
    }

    public Long getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Long consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }
}
