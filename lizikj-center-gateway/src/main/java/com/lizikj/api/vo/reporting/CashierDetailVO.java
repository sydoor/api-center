package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 收银核对信息
 * @author lly
 * @date 20180125
 */
@ApiModel
public class CashierDetailVO {

	
	@ApiModelProperty(value = "现金差额")
    private Long diffAmount;
	@ApiModelProperty(value = "钱箱总额")
    private Long totalAmount;
	@ApiModelProperty(value = "零找钱")
    private Long zeroAmount;
	@ApiModelProperty(value = "实收现金")
    private Long amount;
	public Long getDiffAmount() {
		return diffAmount;
	}
	public void setDiffAmount(Long diffAmount) {
		this.diffAmount = diffAmount;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getZeroAmount() {
		return zeroAmount;
	}
	public void setZeroAmount(Long zeroAmount) {
		this.zeroAmount = zeroAmount;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
}
