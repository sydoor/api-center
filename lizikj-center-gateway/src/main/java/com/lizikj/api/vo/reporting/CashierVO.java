package com.lizikj.api.vo.reporting;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 收银信息
 * @author lly
 * @date 20180125
 */
@ApiModel
public class CashierVO {

	/**
     * 员工ID
     */
	@ApiModelProperty(value = "收银员ID")
    private Long staffId;
	@ApiModelProperty(value = "收银员名称")
    private String staffName;
	@ApiModelProperty(value = "开始收银时间")
	private Date startTime;
	@ApiModelProperty(value = "结束收银时间")
	private Date endTime;
	@ApiModelProperty(value = "零找钱")
	private Long zeroAmount;
	@ApiModelProperty(value = "钱箱总额")
	private Long totalAmount;
	@ApiModelProperty(value = "差异金额")
	private Long diffAmount;
	@ApiModelProperty(value = "差异原因")
	private String diffReason;
	@ApiModelProperty(value = "现金实收金额")
	private Long cashReceivedAmount;
	
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
	public Long getZeroAmount() {
		return zeroAmount;
	}
	public void setZeroAmount(Long zeroAmount) {
		this.zeroAmount = zeroAmount;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getDiffAmount() {
		return diffAmount;
	}
	public void setDiffAmount(Long diffAmount) {
		this.diffAmount = diffAmount;
	}
	public String getDiffReason() {
		return diffReason;
	}
	public void setDiffReason(String diffReason) {
		this.diffReason = diffReason;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getCashReceivedAmount() {
		return cashReceivedAmount;
	}

	public void setCashReceivedAmount(Long cashReceivedAmount) {
		this.cashReceivedAmount = cashReceivedAmount;
	}
}
