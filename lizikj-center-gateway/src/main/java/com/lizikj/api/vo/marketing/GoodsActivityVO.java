package com.lizikj.api.vo.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 美食参与的营销活动
 * 
 * @author lijundong
 * @date 2017年9月5日 下午7:41:40
 */
@ApiModel(value = "美食参与的营销活动结果")
public class GoodsActivityVO {

	@ApiModelProperty(required = true, name = "cutPrice", value = "单品砍价活动信息", dataType = "Object")
	private CutPriceActivityDetailQueryVO cutPrice;

	public CutPriceActivityDetailQueryVO getCutPrice() {
		return cutPrice;
	}

	public void setCutPrice(CutPriceActivityDetailQueryVO cutPrice) {
		this.cutPrice = cutPrice;
	}

}
