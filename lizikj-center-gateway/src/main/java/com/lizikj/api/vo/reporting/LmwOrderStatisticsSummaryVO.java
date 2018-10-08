package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoufe
 * @date 2018/8/13 16:49
 */
@ApiModel
public class LmwOrderStatisticsSummaryVO implements Serializable {

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "报表开始时间")
    private Date startCreateTime;
    @ApiModelProperty(value = "报表结束时间")
    private Date endCreateTime;
    //营业总额 ￥2312.12/132笔
    @ApiModelProperty(value = "营业订单总额")
    private Long totalTotalAmount;
    @ApiModelProperty(value = "营业订单笔数")
    private Long totalOrderCount;
    //闲时 ￥1300.12/60笔
    @ApiModelProperty(value = "闲时订单总额")
    private Long totalIdleTotalAmount;
    @ApiModelProperty(value = "闲时订单笔数")
    private Long totalIdleOrderCount;
    //忙时 ￥1000.00/72笔
    @ApiModelProperty(value = "忙时订单总额")
    private Long totalBusyTotalAmount;
    @ApiModelProperty(value = "忙时订单笔数")
    private Long totalBusyOrderCount;

    //TODO 查询分账接口
    //用shopId去查询
    //闲时进账 ￥1234.12
    @ApiModelProperty(value = "闲时进账总金额")
    private Long totalIdleIncomeAmount;
    @ApiModelProperty(value = "平台补贴总金额")
    private Long totalSubsidyAmount;
    //忙时进账 ￥1234.12
    @ApiModelProperty(value = "忙时进账总金额")
    private Long totalBusyIncomeAmount;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Long getTotalTotalAmount() {
        return totalTotalAmount;
    }

    public void setTotalTotalAmount(Long totalTotalAmount) {
        this.totalTotalAmount = totalTotalAmount;
    }

    public Long getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Long totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public Long getTotalIdleTotalAmount() {
        return totalIdleTotalAmount;
    }

    public void setTotalIdleTotalAmount(Long totalIdleTotalAmount) {
        this.totalIdleTotalAmount = totalIdleTotalAmount;
    }

    public Long getTotalIdleOrderCount() {
        return totalIdleOrderCount;
    }

    public void setTotalIdleOrderCount(Long totalIdleOrderCount) {
        this.totalIdleOrderCount = totalIdleOrderCount;
    }

    public Long getTotalBusyTotalAmount() {
        return totalBusyTotalAmount;
    }

    public void setTotalBusyTotalAmount(Long totalBusyTotalAmount) {
        this.totalBusyTotalAmount = totalBusyTotalAmount;
    }

    public Long getTotalBusyOrderCount() {
        return totalBusyOrderCount;
    }

    public void setTotalBusyOrderCount(Long totalBusyOrderCount) {
        this.totalBusyOrderCount = totalBusyOrderCount;
    }

    public Long getTotalIdleIncomeAmount() {
        return totalIdleIncomeAmount;
    }

    public void setTotalIdleIncomeAmount(Long totalIdleIncomeAmount) {
        this.totalIdleIncomeAmount = totalIdleIncomeAmount;
    }

    public Long getTotalSubsidyAmount() {
        return totalSubsidyAmount;
    }

    public void setTotalSubsidyAmount(Long totalSubsidyAmount) {
        this.totalSubsidyAmount = totalSubsidyAmount;
    }

    public Long getTotalBusyIncomeAmount() {
        return totalBusyIncomeAmount;
    }

    public void setTotalBusyIncomeAmount(Long totalBusyIncomeAmount) {
        this.totalBusyIncomeAmount = totalBusyIncomeAmount;
    }
}
