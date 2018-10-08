package com.lizikj.api.vo.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangxiaolin on 2017/9/22.
 */
@ApiModel
public class PayFlowAccountDetailVO {
    /**
     * 支付流水账号详情ID
     */
    @ApiModelProperty(value = "支付流水账号详情ID")
    private Long id;
    /**
     * 支付流水ID
     */
    @ApiModelProperty(value = "支付流水ID")
    private Long payFlowId;
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 商户名称(冗余)
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 店铺名称(冗余)
     */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    /**
     * 收银员Id
     */
    @ApiModelProperty(value = "收银员Id")
    private Long cashierId;

    /**
     * 收银员名称
     */
    @ApiModelProperty(value = "收银员名称")
    private String cashierName;

    /**
     * 收银员角色
     */
    @ApiModelProperty(value = "收银员角色名称")
    private String cashierRoleName;
    /**
     * 李子会员ID
     */
    @ApiModelProperty(value = "李子会员ID")
    private Long memberId;
    /**
     * 商户会员ID
     */
    @ApiModelProperty(value = "商户会员ID")
    private Long merchantMemberId;
    /**
     * Open ID
     */
    @ApiModelProperty(value = "Open ID")
    private String openId;
    /**
     * 刷卡的银行帐号
     */
    @ApiModelProperty(value = "刷卡的银行帐号")
    private String account;
    /**
     * 银行卡类型:0.未知，1.储蓄卡，2.信用卡
     */
    @ApiModelProperty(value = "银行卡类型:0.未知，1.储蓄卡，2.信用卡")
    private Byte bankCardType;
    /**
     * pos设备号
     */
    @ApiModelProperty(value = "pos设备号")
    private String snNum;
    /**
     * 支付信息
     */
    @ApiModelProperty(value = "支付信息")
    private String payInfo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayFlowId() {
        return payFlowId;
    }

    public void setPayFlowId(Long payFlowId) {
        this.payFlowId = payFlowId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Byte getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(Byte bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getSnNum() {
        return snNum;
    }

    public void setSnNum(String snNum) {
        this.snNum = snNum;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getCashierRoleName() {
        return cashierRoleName;
    }

    public void setCashierRoleName(String cashierRoleName) {
        this.cashierRoleName = cashierRoleName;
    }
}
