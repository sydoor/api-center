package com.lizikj.api.vo.merchandise;

import java.util.List;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 连锁店物品
 * @author Michael.Huang
 * @date 2017/6/28
 */
@ApiModel
public class ChainGoodsVO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 商品ID
	 */
	@ApiModelProperty(value = "商品ID", required = true)
	private String id;
	
	/**
	 * 商户ID
	 */
	@ApiModelProperty(value = "商户ID", required = true)
	private Long merchantId;
	
	/**
     * 图片列表
     */
	@ApiModelProperty(value = "图片列表", required = true)
    private List<Long> mediaIds;
    
    /**
     * 物品名称
     */
	@ApiModelProperty(value = "物品名称", required = true)
    private String goodsName;
	
    /**
     * 分类ID
     */
	@ApiModelProperty(value = "分类ID", required = true)
    private List<String> categoryIds;

    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the merchantId
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return the mediaIds
	 */
	public List<Long> getMediaIds() {
		return mediaIds;
	}

	/**
	 * @param mediaIds the mediaIds to set
	 */
	public void setMediaIds(List<Long> mediaIds) {
		this.mediaIds = mediaIds;
	}

	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @return the categoryIds
	 */
	public List<String> getCategoryIds() {
		return categoryIds;
	}

	/**
	 * @param categoryIds the categoryIds to set
	 */
	public void setCategoryIds(List<String> categoryIds) {
		this.categoryIds = categoryIds;
	}

    
}
