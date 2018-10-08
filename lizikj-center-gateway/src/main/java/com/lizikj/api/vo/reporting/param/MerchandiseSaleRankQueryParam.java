package com.lizikj.api.vo.reporting.param;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MerchandiseSaleRankQueryParam {
	@ApiModelProperty(value = "门店ID", required = true)
	private Long shopId;
	
	@ApiModelProperty(value = "分类ID, 如需查'套餐'分类，该值请填写'套餐'",required = false)
	private String categoryId;
	
	@ApiModelProperty(value = "开始时间",required = false)
	private Date startTime;
	
	@ApiModelProperty(value = "结束时间",required = false)
	private Date endTime;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
