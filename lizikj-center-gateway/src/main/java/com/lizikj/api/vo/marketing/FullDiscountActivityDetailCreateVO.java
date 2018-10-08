package com.lizikj.api.vo.marketing;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 满减优惠创建对象
 * 
 * @author lijundong
 * @date 2017年12月11日 下午4:13:40
 */
@ApiModel
public class FullDiscountActivityDetailCreateVO {

	/**
	 * 满减条件
	 */
	@ApiModelProperty(required = true, name = "satisfy", value = "满减条件", dataType = "Integer")
	private Long satisfy;

	/**
	 * 满减的值
	 */
	@ApiModelProperty(required = true, name = "minus", value = "满减的值", dataType = "Integer")
	private Long minus;

	/**
	 * 开始时间
	 */
	@ApiModelProperty(required = true, name = "startTime", value = "开始时间", dataType = "Date")
	private Date startTime;

	/**
	 * 结束时间
	 */
	@ApiModelProperty(required = true, name = "instructions", value = "结束时间", dataType = "Date")
	private Date endTime;

	/**
	 * 使用说明
	 */
	@ApiModelProperty(required = false, name = "instructions", value = "活动说明", dataType = "String")
	private String instructions;
	
	public Long getSatisfy() {
		return satisfy;
	}

	public void setSatisfy(Long satisfy) {
		this.satisfy = satisfy;
	}

	public Long getMinus() {
		return minus;
	}

	public void setMinus(Long minus) {
		this.minus = minus;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
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
