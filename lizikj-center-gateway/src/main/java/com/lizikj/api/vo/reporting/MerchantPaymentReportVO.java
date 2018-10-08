package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by liangxiaolin on 2017/9/11.
 */
@ApiModel
public class MerchantPaymentReportVO implements Serializable{
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 代理商名称
     */
    @ApiModelProperty(value = "代理商名称")
    private String agentName;
    @ApiModelProperty(value = "总支付金额")
    private Long totalPayAmount;//支付金额
    @ApiModelProperty(value = "总退款金额")
    private Long totalRefundAmount;//退款金额
    @ApiModelProperty(value = "总交易笔数")
    private Integer totalPayNums; //交易笔数
    @ApiModelProperty(value = "总退款笔数")
    private Integer totalRefundNums;//退款笔数
    @ApiModelProperty(value = "现金支付金额")
    private Long cashPayAmount;//现金支付金额
    @ApiModelProperty(value = "现金退款金额")
    private Long cashRefundAmount;//退款金额
    @ApiModelProperty(value = "刷卡交易笔数")
    private Integer cashPayNums; //交易笔数
    @ApiModelProperty(value = "刷卡退款笔数")
    private Integer cashRefundNums;//退款笔数
    @ApiModelProperty(value = "刷卡交易金额")
    private Long cardPayAmount;//刷卡金额
    @ApiModelProperty(value = "刷卡退款金额")
    private Long cardRefundAmount;//刷卡退款金额
    @ApiModelProperty(value = "刷卡交易笔数")
    private Integer cardPayNums; //交易笔数
    @ApiModelProperty(value = "刷卡退款笔数")
    private Integer cardRefundNums;//退款笔数
    @ApiModelProperty(value = "微信交易金额")
    private Long wechatPayAmount;//微信支付金额
    @ApiModelProperty(value = "微信退款金额")
    private Long wechatRefundAmount;//退款金额
    @ApiModelProperty(value = "微信交易笔数")
    private Integer wechatPayNums; //交易笔数
    @ApiModelProperty(value = "微信退款笔数")
    private Integer wechatRefundNums;//退款笔数
    @ApiModelProperty(value = "支付宝交易金额")
    private Long aliPayAmount;// 支付宝支付金
    @ApiModelProperty(value = "支付宝退款金额")
    private Long aliRefundAmount;//退款金额
    @ApiModelProperty(value = "支付宝交易笔数")
    private Integer aliPayNums; //交易笔数
    @ApiModelProperty(value = "支付宝退款笔数")
    private Integer aliRefundNums;//退款笔数

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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Long getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(Long totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public Long getTotalRefundAmount() {
        return totalRefundAmount;
    }

    public void setTotalRefundAmount(Long totalRefundAmount) {
        this.totalRefundAmount = totalRefundAmount;
    }

    public Integer getTotalPayNums() {
        return totalPayNums;
    }

    public void setTotalPayNums(Integer totalPayNums) {
        this.totalPayNums = totalPayNums;
    }

    public Integer getTotalRefundNums() {
        return totalRefundNums;
    }

    public void setTotalRefundNums(Integer totalRefundNums) {
        this.totalRefundNums = totalRefundNums;
    }

    public Long getCashPayAmount() {
        return cashPayAmount;
    }

    public void setCashPayAmount(Long cashPayAmount) {
        this.cashPayAmount = cashPayAmount;
    }

    public Long getCashRefundAmount() {
        return cashRefundAmount;
    }

    public void setCashRefundAmount(Long cashRefundAmount) {
        this.cashRefundAmount = cashRefundAmount;
    }

    public Integer getCashPayNums() {
        return cashPayNums;
    }

    public void setCashPayNums(Integer cashPayNums) {
        this.cashPayNums = cashPayNums;
    }

    public Integer getCashRefundNums() {
        return cashRefundNums;
    }

    public void setCashRefundNums(Integer cashRefundNums) {
        this.cashRefundNums = cashRefundNums;
    }

    public Long getCardPayAmount() {
        return cardPayAmount;
    }

    public void setCardPayAmount(Long cardPayAmount) {
        this.cardPayAmount = cardPayAmount;
    }

    public Long getCardRefundAmount() {
        return cardRefundAmount;
    }

    public void setCardRefundAmount(Long cardRefundAmount) {
        this.cardRefundAmount = cardRefundAmount;
    }

    public Integer getCardPayNums() {
        return cardPayNums;
    }

    public void setCardPayNums(Integer cardPayNums) {
        this.cardPayNums = cardPayNums;
    }

    public Integer getCardRefundNums() {
        return cardRefundNums;
    }

    public void setCardRefundNums(Integer cardRefundNums) {
        this.cardRefundNums = cardRefundNums;
    }

    public Long getWechatPayAmount() {
        return wechatPayAmount;
    }

    public void setWechatPayAmount(Long wechatPayAmount) {
        this.wechatPayAmount = wechatPayAmount;
    }

    public Long getWechatRefundAmount() {
        return wechatRefundAmount;
    }

    public void setWechatRefundAmount(Long wechatRefundAmount) {
        this.wechatRefundAmount = wechatRefundAmount;
    }

    public Integer getWechatPayNums() {
        return wechatPayNums;
    }

    public void setWechatPayNums(Integer wechatPayNums) {
        this.wechatPayNums = wechatPayNums;
    }

    public Integer getWechatRefundNums() {
        return wechatRefundNums;
    }

    public void setWechatRefundNums(Integer wechatRefundNums) {
        this.wechatRefundNums = wechatRefundNums;
    }

    public Long getAliPayAmount() {
        return aliPayAmount;
    }

    public void setAliPayAmount(Long aliPayAmount) {
        this.aliPayAmount = aliPayAmount;
    }

    public Long getAliRefundAmount() {
        return aliRefundAmount;
    }

    public void setAliRefundAmount(Long aliRefundAmount) {
        this.aliRefundAmount = aliRefundAmount;
    }

    public Integer getAliPayNums() {
        return aliPayNums;
    }

    public void setAliPayNums(Integer aliPayNums) {
        this.aliPayNums = aliPayNums;
    }

    public Integer getAliRefundNums() {
        return aliRefundNums;
    }

    public void setAliRefundNums(Integer aliRefundNums) {
        this.aliRefundNums = aliRefundNums;
    }
}
