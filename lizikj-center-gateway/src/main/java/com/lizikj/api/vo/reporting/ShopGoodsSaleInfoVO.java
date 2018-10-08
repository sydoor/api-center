package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author Michael.Huang
 * @date 2018/7/20 10:35
 */
@ApiModel
public class ShopGoodsSaleInfoVO {

    @ApiModelProperty(value = "订单物品ID")
    private Long orderItemId;
    /**
     * 销售数量
     */
    @ApiModelProperty(value = "销售数量")
    private Long sellVolume;

    /**
     * 记重
     */
    @ApiModelProperty(value = "记重")
    private Long weight;

    /**
     * 出售价格
     */
    @ApiModelProperty(value = "出售价格")
    private Long sellPrice;

    /**
     * 会员价
     */
    @ApiModelProperty(value = "会员价")
    private Long memberPrice;

    /**
     * 成本金额
     */
    @ApiModelProperty(value = "成本金额")
    private Long costAmount;

    /**
     * 总金额
     */
    @ApiModelProperty(value = "总金额")
    private Long totalAmount;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private Long benefitAmount;

    /**
     * 提成金额
     */
    @ApiModelProperty(value = "提成金额")
    private Long salesExtractAmount;

    /**
     * 物品id
     */
    @ApiModelProperty(value = "物品id")
    private String goodsId;

    /**
     * 物品名称
     */
    @ApiModelProperty(value = "物品名称")
    private String goodsName;

    /**
     * 单位
     */
    @ApiModelProperty(value = "美食单位")
    private String unit;

    /**
     * skuId
     */
    @ApiModelProperty(value = "skuId")
    private Long skuId;

    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Long merchantId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 下单时间
     */
    @ApiModelProperty(value = "下单时间")
    private Date orderTime;

    /**
     * 下单员工
     */
    @ApiModelProperty(value = "下单员工ID")
    private Long orderPersonId;

    /**
     * String
     */
    @ApiModelProperty(value = "下单员工")
    private String orderPersonName;

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

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(Long sellVolume) {
        this.sellVolume = sellVolume;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Long getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Long memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Long getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Long costAmount) {
        this.costAmount = costAmount;
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

    public Long getSalesExtractAmount() {
        return salesExtractAmount;
    }

    public void setSalesExtractAmount(Long salesExtractAmount) {
        this.salesExtractAmount = salesExtractAmount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getOrderPersonId() {
        return orderPersonId;
    }

    public void setOrderPersonId(Long orderPersonId) {
        this.orderPersonId = orderPersonId;
    }

    public String getOrderPersonName() {
        return orderPersonName;
    }

    public void setOrderPersonName(String orderPersonName) {
        this.orderPersonName = orderPersonName;
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

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
    
    
}
