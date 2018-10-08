package com.lizikj.api.vo.order;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 取消订单原因VO
 * @author zone
 * @date 2018年1月19日
 *
 */
@ApiModel
public class OrderCancelReasonInfoVO {
    @ApiModelProperty(value = "订单号", required = true)
    private String orderNo;

    @ApiModelProperty(value = "操作员工id", required = true)
    private Long operatorStaffId;

    @ApiModelProperty(value = "授权码", required = true)
    private String authorCode;

    @ApiModelProperty(value = "取消原因类型  1.菜品售罄，2.上错菜，3.顾客个人原因，4.菜品质量问题，0.其他", required = true)
    private Byte cancelReasonType;

    @ApiModelProperty(value = "取消原因", required = true)
    private String cancelReason;
    
    @ApiModelProperty(value = "取消订单的物品list", required = true)
    private List<OrderCancelReasonItemVO> orderCancelReasonItems;

    @ApiModelProperty(value = "授权者姓名")
    private String authorStaffName;
    
    @ApiModelProperty(value = "操作员工姓名")
    private String operatorStaffName;
    
    @ApiModelProperty(value = "操作人员工角色")
    private String operatorStaffRoleName;
    
    @ApiModelProperty(value = "取消时间")
    private Date cancelTime;
    
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getOperatorStaffId() {
		return operatorStaffId;
	}

	public void setOperatorStaffId(Long operatorStaffId) {
		this.operatorStaffId = operatorStaffId;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public Byte getCancelReasonType() {
		return cancelReasonType;
	}

	public void setCancelReasonType(Byte cancelReasonType) {
		this.cancelReasonType = cancelReasonType;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public List<OrderCancelReasonItemVO> getOrderCancelReasonItems() {
		return orderCancelReasonItems;
	}

	public void setOrderCancelReasonItems(List<OrderCancelReasonItemVO> orderCancelReasonItems) {
		this.orderCancelReasonItems = orderCancelReasonItems;
	}

	public String getAuthorStaffName() {
		return authorStaffName;
	}

	public void setAuthorStaffName(String authorStaffName) {
		this.authorStaffName = authorStaffName;
	}

	public String getOperatorStaffName() {
		return operatorStaffName;
	}

	public void setOperatorStaffName(String operatorStaffName) {
		this.operatorStaffName = operatorStaffName;
	}

	public String getOperatorStaffRoleName() {
		return operatorStaffRoleName;
	}

	public void setOperatorStaffRoleName(String operatorStaffRoleName) {
		this.operatorStaffRoleName = operatorStaffRoleName;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
}