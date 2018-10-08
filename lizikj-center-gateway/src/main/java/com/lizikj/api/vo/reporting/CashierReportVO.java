package com.lizikj.api.vo.reporting;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CashierReportVO {
	 /**
     * 收银ID
     */
	@ApiModelProperty(value = "收银ID")
    private Long cashierId;
    /**
     * 员工名称
     */
	@ApiModelProperty(value = "员工名称")
    private String staffName;
    /**
     * 开班时间
     */
	@ApiModelProperty(value = "开班时间")
    private Date startTime;
    /**
     * 结班时间
     */
	@ApiModelProperty(value = "结班时间")
    private Date endTime;

    /**
     * 营业收入
     */
	@ApiModelProperty(value = "营业收入")
    private Long totalAmount;

	public Long getCashierId() {
		return cashierId;
	}

	public void setCashierId(Long cashierId) {
		this.cashierId = cashierId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
}
