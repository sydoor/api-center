package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Michael.Huang
 * @date 2018/7/20 10:35
 */
@ApiModel
public class ShopGoodsProduceReportVO {
    /**
     * 物品ID
     */
    @ApiModelProperty(value = "物品ID")
    private String goodsId;
    /**
     * 物品名称
     */
    @ApiModelProperty(value = "物品名称")
    private String goodsName;
    /**
     * SKUID
     */
    @ApiModelProperty(value = "SKUID")
    private Long skuId;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 物品编号
     */
    @ApiModelProperty(value = "物品编号")
    private String goodsNumber;
    /**
     * 分类列表
     */
    @ApiModelProperty(value = "分类列表")
    private List<String> categorys;
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
     * 优化金额
     */
    @ApiModelProperty(value = "优化金额")
    private Long saleBenefit;
    /**
     * 提出金额
     */
    @ApiModelProperty(value = "提出金额")
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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public List<String> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<String> categorys) {
        this.categorys = categorys;
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
