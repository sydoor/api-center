package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/8/10.
 */
@ApiModel
public class MerchandiseCategoryReportVO {
    /**
     * 统计日期
     */
    @ApiModelProperty(value = "统计日期")
    private Date reportDate;
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
     * 美食销量
     */
    @ApiModelProperty(value = "美食销量")
    private Integer saleNum;
    /**
     * 美食销量（数量）
     */
    @ApiModelProperty(value = "美食销量（数量）")
    private Integer saleQuantity;
    /**
     * 销售额
     */
    @ApiModelProperty(value = "销售额")
    private Double saleAmount;
    /**
     * 成本
     */
    @ApiModelProperty(value = "成本")
    private Double saleCost;
    /**
     * 毛利润
     */
    @ApiModelProperty(value = "毛利润")
    private Double saleProfit;

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(Integer saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Double getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(Double saleCost) {
        this.saleCost = saleCost;
    }

    public Double getSaleProfit() {
        return saleProfit;
    }

    public void setSaleProfit(Double saleProfit) {
        this.saleProfit = saleProfit;
    }
}
