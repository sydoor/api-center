package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 美食信息对象
 * @author lijundong
 * @date 2017年9月8日 下午4:39:48
 */
@ApiModel(value = "美食信息对象")
public class GoodsVO {

	/**
	 * 美食名称
	 */
	@ApiModelProperty(required = true, name = "goodsName", value = "美食名称", dataType = "String")
	private String goodsName;

	/**
	 * 美食图片，七牛id
	 */
	@ApiModelProperty(required = true, name = "goodsImg", value = "美食图片，七牛id", dataType = "Long")
	private long goodsImg;

	/**
	 * 原价
	 */
	@ApiModelProperty(required = true, name = "sellPrice", value = "美食原价", dataType = "Long")
	private long sellPrice;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public long getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(long goodsImg) {
		this.goodsImg = goodsImg;
	}

	public long getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(long sellPrice) {
		this.sellPrice = sellPrice;
	}

}
