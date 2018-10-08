package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 单品砍价页面VO对象
 * 
 * @author lijundong
 * @date 2017年9月8日 下午4:17:31
 */
@ApiModel(value = "单品砍价页面VO对象")
public class CutPriceSinglePageVO {

	/**
	 * 美食信息
	 */
	@ApiModelProperty(required = true, name = "goods", value = "美食信息", dataType = "Object")
	private GoodsVO goods;

	/**
	 * 最低价
	 */
	@ApiModelProperty(required = true, name = "minPrice", value = "最低价", dataType = "Long")
	private long minPrice;

	public GoodsVO getGoods() {
		return goods;
	}

	public void setGoods(GoodsVO goods) {
		this.goods = goods;
	}

	public long getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(long minPrice) {
		this.minPrice = minPrice;
	}
}
