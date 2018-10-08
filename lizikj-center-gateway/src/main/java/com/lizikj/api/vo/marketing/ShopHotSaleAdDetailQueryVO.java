package com.lizikj.api.vo.marketing;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.lizikj.api.vo.merchandise.GoodsVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- shop_hot_sale_ad_detail Created by lijundong on 2017-20-17
 * 16:20:47
 */
@ApiModel(value = "查询营销广告对象")
public class ShopHotSaleAdDetailQueryVO {
	/**
	 * 热销广告
	 */
	@ApiModelProperty(required = true, name = "hotSaleAdDetailId", value = "营销广告ID", dataType = "Long")
	private Long hotSaleAdDetailId;

	/**
	 * 店铺的营销活动ID
	 */
	@ApiModelProperty(required = true, name = "shopId", value = "店铺ID", dataType = "Long")
	private Long shopId;

	/**
	 * 广告图片
	 */
	@ApiModelProperty(required = true, name = "adName", value = "广告图片", dataType = "String")
	private Long mediaId;

	/**
	 * 营销广告的排序
	 */
	@ApiModelProperty(required = true, name = "sort", value = "营销广告的排序, 0最小，2最大", dataType = "String")
	private Integer sort;

	@ApiModelProperty(required = true, name = "goods", value = "美食对象", dataType = "Object")
	private GoodsVO goods;
	
	public Long getHotSaleAdDetailId() {
		return hotSaleAdDetailId;
	}

	public void setHotSaleAdDetailId(Long hotSaleAdDetailId) {
		this.hotSaleAdDetailId = hotSaleAdDetailId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public GoodsVO getGoods() {
		return goods;
	}

	public void setGoods(GoodsVO goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}