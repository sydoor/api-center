package com.lizikj.api.vo.marketing;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- shop_hot_sale_ad_detail Created by lijundong on 2017-20-17
 * 16:20:47
 */
@ApiModel(value = "美食推荐排序对象")
public class ShopHotSaleAdDetailSortVO {
	/**
	 * 热销广告
	 */
	@ApiModelProperty(required = true, name = "hotSaleAdDetailId", value = "营销广告ID", dataType = "Long")
	private Long hotSaleAdDetailId;

	/**
	 * 营销广告的美食ID
	 */
	@ApiModelProperty(required = true, name = "sort", value = "排序id：从0开始，0排第一，2排最后", dataType = "Integer")
	private Integer sort;

	public Long getHotSaleAdDetailId() {
		return hotSaleAdDetailId;
	}

	public void setHotSaleAdDetailId(Long hotSaleAdDetailId) {
		this.hotSaleAdDetailId = hotSaleAdDetailId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}