package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author lxl
 * @Date 2018/7/5 17:42
 */
@ApiModel
public class BusinessSettlePlanVO {
    @ApiModelProperty(value = "清机记录ID")
    private Long planId;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    @ApiModelProperty(value = "清机日期")
    private Date settleDate;
    @ApiModelProperty(value = "清机序号")
    private Long printSeq;
    @ApiModelProperty(value = "打印状态")
    private Boolean printStatus;
    @ApiModelProperty(value = "清机时营业额")
    private Long amount;
    @ApiModelProperty(value = "清机交易笔数")
    private Integer num;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
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

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Long getPrintSeq() {
        return printSeq;
    }

    public void setPrintSeq(Long printSeq) {
        this.printSeq = printSeq;
    }

    public Boolean getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(Boolean printStatus) {
        this.printStatus = printStatus;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
