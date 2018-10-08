package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoufe
 * @date 2017/9/26 10:53
 */
@ApiModel
public class SyncPayFlowAccountDetailVO implements Serializable {

    private static final long serialVersionUID = 2156905087351090021L;

    @ApiModelProperty(value = "支付详情ID", required = true)
    private Long id;

    @ApiModelProperty(value = "支付流水ID", required = true)
    private Long payFlowId;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "收银员ID")
    private Long cashierId;

    @ApiModelProperty(value = "李子会员ID")
    private Long memberId;

    @ApiModelProperty(value = "商户会员ID")
    private Long merchantMemberId;

    @ApiModelProperty(value = "公众号ID")
    private String openId;

    @ApiModelProperty(value = "刷卡的银行帐号")
    private String account;

    @ApiModelProperty(value = "银行卡类型:0.未知，1.储蓄卡，2.信用卡")
    private Byte bankCardType;

    @ApiModelProperty(value = "POS的SN号")
    private String snNum;

    @ApiModelProperty(value = "支付sdk信息")
    private String payInfo;

    //private Byte removeStatus;

//    @ApiModelProperty(value = "POS的创建时间", required = true)
//    private Date createTime;
//
//    @ApiModelProperty(value = "POS的关系时间", required = true)
//    private Date updateTime;


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

//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
}
