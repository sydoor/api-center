package com.lizikj.api.vo.marketing;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "分时段优惠编辑对象")
public class TimeDiscountActivityDetailUpdateVO {

	@ApiModelProperty(required = true, name = "timeDiscountId", value = "分时段优惠id", dataType = "Integer")
	private Long timeDiscountId;

	@ApiModelProperty(required = true, name = "status", value = "是否启用优惠，0=不启用，1=启用", dataType = "Integer")
	private Byte status;

	@ApiModelProperty(required = true, name = "mode", value = "优惠方式，1=按结账时间；2=按下单时间", dataType = "Integer")
	private Byte mode;

	@ApiModelProperty(required = true, name = "startTime", value = "起始时间，单位秒，12：00=12*3600=43200", dataType = "Integer")
	private Integer startTime;

	@ApiModelProperty(required = true, name = "endTime", value = "结束时间，单位秒，14：00=12*3600=50400", dataType = "Integer")
	private Integer endTime;

	@ApiModelProperty(required = true, name = "discount", value = "折扣，8折=80，9.9折=99", dataType = "Integer")
	private Integer discount;

	@ApiModelProperty(required = true, name = "categoryIds", value = "美食分类id列表", dataType = "Object")
	private List<String> categoryIds;

	@ApiModelProperty(required = true, name = "goodsIds", value = "美食id列表", dataType = "Object")
	private List<String> goodsIds;

	@ApiModelProperty(required = true, name = "weeks", value = "重复时间：周一=1，周二=2,周三=3,周四=4,周五=5,周六=6，周日=7；", dataType = "String")
	private String weeks;

	public Long getTimeDiscountId() {
		return timeDiscountId;
	}

	public void setTimeDiscountId(Long timeDiscountId) {
		this.timeDiscountId = timeDiscountId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getMode() {
		return mode;
	}

	public void setMode(Byte mode) {
		this.mode = mode;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public List<String> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<String> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public List<String> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(List<String> goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	@Override
	public String toString() {
		return "TimeDiscountActivityDetailUpdateVO [timeDiscountId=" + timeDiscountId + ", status=" + status + ", mode="
				+ mode + ", startTime=" + startTime + ", endTime=" + endTime + ", discount=" + discount
				+ ", categoryIds=" + categoryIds + ", goodsIds=" + goodsIds + ", weeks=" + weeks + "]";
	}

}
