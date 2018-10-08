package com.lizikj.api.vo.marketing;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "分时段优惠开关对象")
public class TimeDiscountSwitchAllVO {

	@ApiModelProperty(required = true, name = "timeDiscountIds", value = "分时优惠活动id集合", dataType = "Object")
	private List<Long> timeDiscountIds;

	@ApiModelProperty(required = true, name = "status", value = "是否启用优惠，0=不启用，1=启用", dataType = "Integer")
	private Byte status;

	public List<Long> getTimeDiscountIds() {
		return timeDiscountIds;
	}

	public void setTimeDiscountIds(List<Long> timeDiscountIds) {
		this.timeDiscountIds = timeDiscountIds;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TimeDiscountSwitchAllVO [timeDiscountIds=" + timeDiscountIds + ", status=" + status + "]";
	}

}
