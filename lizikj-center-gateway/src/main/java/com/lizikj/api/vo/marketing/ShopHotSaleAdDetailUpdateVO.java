package com.lizikj.api.vo.marketing;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- shop_hot_sale_ad_detail Created by lijundong on 2017-20-17
 * 16:20:47
 */
@ApiModel(value = "编辑营销广告对象")
public class ShopHotSaleAdDetailUpdateVO {
	/**
	 * 热销广告
	 */
	@ApiModelProperty(required = true, name = "hotSaleAdDetailId", value = "营销广告ID", dataType = "Long")
	private Long hotSaleAdDetailId;

	/**
	 * 广告图片
	 */
	@ApiModelProperty(required = true, name = "mediaId", value = "广告图片", dataType = "String")
	private Long mediaId;

	/**
	 * 营销广告的美食ID
	 */
	@ApiModelProperty(required = true, name = "goodsId", value = "营销广告的美食ID", dataType = "Long")
	private String goodsId;

	public Long getHotSaleAdDetailId() {
		return hotSaleAdDetailId;
	}

	public void setHotSaleAdDetailId(Long hotSaleAdDetailId) {
		this.hotSaleAdDetailId = hotSaleAdDetailId;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}