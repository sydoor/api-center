package com.lizikj.api.vo.reporting;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 美食销量排行详情
 * 
 * @author liaojw 
 * @date 2018年7月18日 下午2:21:48
 */
@ApiModel
public class MerchandiseSaleRankDetailVO{
    /**
     * 店铺ID
     */
	@ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 美食ID
     */
	@ApiModelProperty(value = "美食ID")
    private String goodsId;
    /**
     * 美食名称
     */
	@ApiModelProperty(value = "美食名称")
    private String goodsName;
    /**
     * 销量（数量，份）
     */
	@ApiModelProperty(value = "销量（数量，份）")
    private Integer saleQuantity;
    /**
     * 销售金额
     */
	@ApiModelProperty(value = "销售金额")
    private Long saleAmount;
    /**
     * skuId 
     */
	@ApiModelProperty(value = "skuId ")
    private Long skuId;
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
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
	public Integer getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(Integer saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	public Long getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(Long saleAmount) {
		this.saleAmount = saleAmount;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

}
