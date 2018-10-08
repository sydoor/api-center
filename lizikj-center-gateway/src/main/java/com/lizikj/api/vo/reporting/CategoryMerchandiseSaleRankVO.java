package com.lizikj.api.vo.reporting;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 美食销售排行统计
 * 
 * @author liaojw 
 * @date 2018年7月18日 下午2:15:51
 */
@ApiModel
public class CategoryMerchandiseSaleRankVO{
	/**
	 * 美食分类ID
	 */
	@ApiModelProperty(value = "美食分类ID")
	private String categoryId;
	
	/**
	 * 美食分类名称
	 */
	@ApiModelProperty(value = "美食分类名称")
	private String categoryName;
	
	/**
	 * 门店ID
	 */
	@ApiModelProperty(value = "门店ID")
	private Long shopId;
	
	/**
	 * 美食列表
	 */
	@ApiModelProperty(value = "美食列表")
	private List<MerchandiseSaleRankDetailVO> items;
	
	/**
	 * 组类型 1：总排行 2：分类美食
	 */
	@ApiModelProperty(value = "组类型 1：总排行 2：分类美食")
	private Byte groupType;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public List<MerchandiseSaleRankDetailVO> getItems() {
		return items;
	}

	public void setItems(List<MerchandiseSaleRankDetailVO> items) {
		this.items = items;
	}

	public Byte getGroupType() {
		return groupType;
	}

	public void setGroupType(Byte groupType) {
		this.groupType = groupType;
	}
	
	
}
