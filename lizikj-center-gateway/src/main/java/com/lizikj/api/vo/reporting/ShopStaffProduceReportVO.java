package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michael.Huang
 * @date 2018/7/20 10:59
 */
@ApiModel
public class ShopStaffProduceReportVO {

    /**
     * 员工ID
     */
    @ApiModelProperty(value = "员工ID")
    private Long staffId;
    /**
     * 员工名册
     */
    @ApiModelProperty(value = "员工名册")
    private String staffName;
    /**
     * 下单笔数
     */
    @ApiModelProperty(value = "下单笔数")
    private Integer orderCount;
    /**
     * 点餐次数
     */
    @ApiModelProperty(value = "点餐次数")
    private Integer orderTimes;
    /**
     * 减菜次数
     */
    @ApiModelProperty(value = "减菜次数")
    private Integer reduceTimes;
    /**
     * 消费次数
     */
    @ApiModelProperty(value = "消费次数")
    private Integer consumeTimes;
    /**
     * 赠送次数
     */
    @ApiModelProperty(value = "赠送次数")
    private Integer giveTimes;
    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    private Long totalAmount;
    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额")
    private Long saleAmount;
    /**
     * 成本金额
     */
    @ApiModelProperty(value = "成本金额")
    private Long saleCost;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private Long saleBenefit;
    /**
     * 提成金额
     */
    @ApiModelProperty(value = "提成金额")
    private Long saleExtract;

    /**
     * 是否是汇总的那一行数据
     */
    private Boolean sumRow;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getOrderTimes() {
        return orderTimes;
    }

    public void setOrderTimes(Integer orderTimes) {
        this.orderTimes = orderTimes;
    }

    public Integer getReduceTimes() {
        return reduceTimes;
    }

    public void setReduceTimes(Integer reduceTimes) {
        this.reduceTimes = reduceTimes;
    }

    public Integer getConsumeTimes() {
        return consumeTimes;
    }

    public void setConsumeTimes(Integer consumeTimes) {
        this.consumeTimes = consumeTimes;
    }

    public Integer getGiveTimes() {
        return giveTimes;
    }

    public void setGiveTimes(Integer giveTimes) {
        this.giveTimes = giveTimes;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Long saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Long getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(Long saleCost) {
        this.saleCost = saleCost;
    }

    public Long getSaleBenefit() {
        return saleBenefit;
    }

    public void setSaleBenefit(Long saleBenefit) {
        this.saleBenefit = saleBenefit;
    }

    public Long getSaleExtract() {
        return saleExtract;
    }

    public void setSaleExtract(Long saleExtract) {
        this.saleExtract = saleExtract;
    }

    public Boolean getSumRow() {
        return sumRow;
    }

    public void setSumRow(Boolean sumRow) {
        this.sumRow = sumRow;
    }
}
