package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 时间段模型
 * 
 * @author liaojw 
 * @date 2018年7月5日 下午2:49:06
 */
@ApiModel
public class TimeSectionModelVO {
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间")
	private String activeStartTime;
	
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间")
	private String activeEndTime;

	public String getActiveStartTime() {
		return activeStartTime;
	}

	public void setActiveStartTime(String activeStartTime) {
		this.activeStartTime = activeStartTime;
	}

	public String getActiveEndTime() {
		return activeEndTime;
	}

	public void setActiveEndTime(String activeEndTime) {
		this.activeEndTime = activeEndTime;
	}

	
}
