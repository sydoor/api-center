package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 营销餐饮标签
 * 
 * @author liaojw 
 * @date 2018年6月28日 下午3:01:58
 */
@ApiModel
public class ShopMarketingCateringTagVO {
	@ApiModelProperty(value = "id")
	private String id;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称")
	private String tagName;
	/**
	 * 上级标签
	 */
	@ApiModelProperty(value = "上级标签")
	private String parentTagId;
	
	@ApiModelProperty(value = "叶子")
	private List<ShopMarketingCateringTagVO> childrens;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getParentTagId() {
		return parentTagId;
	}

	public void setParentTagId(String parentTagId) {
		this.parentTagId = parentTagId;
	}

	public List<ShopMarketingCateringTagVO> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<ShopMarketingCateringTagVO> childrens) {
		this.childrens = childrens;
	}

	
}
