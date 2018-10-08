package com.lizikj.api.vo.member;

import com.lizikj.common.enums.PaymentTypeEnum;
import com.lizikj.payment.pay.enums.PayFlowTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by liangxiaolin on 2017/10/9.
 */
@ApiModel
public class MemberOrderFlowVO {
    /**
     * 会员基础信息id
     */
    @ApiModelProperty(value = "会员基础信息id")
    private Long memberId;
    /**
     * 商户会员id
     */
    @ApiModelProperty(value = "商户会员id")
    private Long merchantMemberId;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 用户真实姓名
     */
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    /**
     * 订单总金额
     */
    @ApiModelProperty(value = "订单总金额")
    private Long totalAmount;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private Long benefitAmount;
    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private Long payAmount;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    /**
     * 支付方式列表
     */
    @ApiModelProperty(value = "支付方式列表，一个订单会有多个支付")
    private String paymentTypeCodeNames;

    @ApiModelProperty(value = "支付方式列表 支付方式 1:支付宝  2:微信  3:银联钱包  4:刷卡  5:现金  6:云闪付  8:会员  9:优惠券  10:美团券  12:预付券")
    private List<Byte> paymentTypeCodes;

    @ApiModelProperty(value = "交易单号列表")
    private List<Long> innerTradeNos;
    /**
     * 获得积分
     */
    @ApiModelProperty(value = "获得积分")
    private Long credit;

    @ApiModelProperty(value = "交易时间")
    private Date tradeTime;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMerchantMemberId() {
        return merchantMemberId;
    }

    public void setMerchantMemberId(Long merchantMemberId) {
        this.merchantMemberId = merchantMemberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaymentTypeCodeNames() {
        return paymentTypeCodeNames;
    }

    public void setPaymentTypeCodeNames(String paymentTypeCodeNames) {
        this.paymentTypeCodeNames = paymentTypeCodeNames;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public List<Byte> getPaymentTypeCodes() {
        return paymentTypeCodes;
    }

    public void setPaymentTypeCodes(List<Byte> paymentTypeCodes) {
        this.paymentTypeCodes = paymentTypeCodes;
    }

    public List<Long> getInnerTradeNos() {
        return innerTradeNos;
    }

    public void setInnerTradeNos(List<Long> innerTradeNos) {
        this.innerTradeNos = innerTradeNos;
    }
}
