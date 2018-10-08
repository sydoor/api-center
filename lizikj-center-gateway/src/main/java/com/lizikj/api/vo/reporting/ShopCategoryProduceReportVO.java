package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michael.Huang
 * @date 2018/7/20 10:36
 */
@ApiModel
public class ShopCategoryProduceReportVO {

    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID")
    private String categoryId;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String categoryName;
    /**
     * 下单次数
     */
    @ApiModelProperty(value = "下单次数")
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
    @ApiModelProperty(value = "实收金额")
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
    @ApiModelProperty("是否是汇总的那一行数据")
    private Boolean sumRow;
    public Boolean getSumRow() {
        return sumRow;
    }

    public void setSumRow(Boolean sumRow) {
        this.sumRow = sumRow;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
}
