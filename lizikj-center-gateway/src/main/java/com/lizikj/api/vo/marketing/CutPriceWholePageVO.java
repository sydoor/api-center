package com.lizikj.api.vo.marketing;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 整单砍价页面VO对象
 * 
 * @author lijundong
 * @date 2017年9月8日 下午4:17:31
 */
@ApiModel(value = "整单砍价页面VO对象")
public class CutPriceWholePageVO {

	/**
	 * 几折优惠：假如7.8折，返回78
	 */
	@ApiModelProperty(required = true, name = "discount", value = "几折优惠：假如7.8折，返回78", dataType = "Integer")
	private int discount;

	/**
	 * 订单物品列表
	 */
	@ApiModelProperty(required = true, name = "goodsList", value = "订单物品列表", dataType = "Object")
	private List<GoodsVO> goodsList;

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public List<GoodsVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsVO> goodsList) {
		this.goodsList = goodsList;
	}

}
