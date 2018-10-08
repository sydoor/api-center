package com.lizikj.api.vo.order;

import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.order.enums.RefundAmountTypeEnum;
import com.lizikj.order.enums.RefundReasonTypeEnum;
import com.lizikj.order.enums.RefundTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/8/22 15:05
 */
@ApiModel
public class OrderRefundReasonInfoVO implements Serializable {


    private static final long serialVersionUID = 2497083613691828853L;
    /**
     * 退款信息ID
     */
    @ApiModelProperty(value = "退款信息ID：如果该订单已经填写了一次原因就不能再填写")
    private Long refundReasonId;

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    private Long refundAmount;

    /**
     * 退款金额类型：1.全额，2.部分金额
     */
    @ApiModelProperty(value = "退款金额类型：见RefundAmountTypeEnum：ALL.全额，PART.部分金额")
    private RefundAmountTypeEnum refundAmountType;

    /**
     * 退款方式：1.现金退款，2.原路退回
     */
    @ApiModelProperty(value = "退款方式：1.现金退款，2.原路退回")
    private RefundTypeEnum refundType;

    /**
     * 操作人员工ID
     */
    @ApiModelProperty(value = "操作人员工ID")
    private Long operatorStaffId;

    /**
     * 操作人员工名称
     */
    @ApiModelProperty(value = "操作人员工名称")
    private String operatorStaffName;


    /**
     * 操作人员工角色
     */
    @ApiModelProperty(value = "操作人员工角色")
    private String operatorStaffRoleName;


    /**
     * 授权码
     */
    @ApiModelProperty(value = "授权码：退款时需要输入", required = true)
    private String authorCode;

    /**
     * 授权人员工ID
     */
    @ApiModelProperty(value = "授权人员工ID")
    private Long authorStaffId;


    /**
     * 授权人员工名称
     */
    @ApiModelProperty(value = "授权人员工名称")
    private String authorStaffName;

    /**
     * 退款原因类型：1.菜品售罄，2.上错菜，3.顾客个人原因，4.菜品质量问题，0.其他
     */
    @ApiModelProperty(value = "退款原因类型：1.菜品售罄，2.上错菜，3.顾客个人原因，4.菜品质量问题，0.其他")
    private RefundReasonTypeEnum refundReasonType;

    /**
     * 退款原因
     */
    @ApiModelProperty(value = "退款原因")
    private String refundReason;

    /**
     * 退款时间
     */
    @ApiModelProperty(value = "退款时间")
    private Date refundTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否删除：1.已删除，0.未删除
     */
    @ApiModelProperty(value = "是否删除：1.已删除，0.未删除")
    private RemoveStatusEnum removeStatus;

    /**
     * 退款商品
     */
    @ApiModelProperty(value = "退款商品")
    private List<OrderRefundReasonItemVO> orderRefundReasonItems;


    public Long getRefundReasonId() {
        return refundReasonId;
    }

    public void setRefundReasonId(Long refundReasonId) {
        this.refundReasonId = refundReasonId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public RefundAmountTypeEnum getRefundAmountType() {
        return refundAmountType;
    }

    public void setRefundAmountType(RefundAmountTypeEnum refundAmountType) {
        this.refundAmountType = refundAmountType;
    }

    public RefundTypeEnum getRefundType() {
        return refundType;
    }

    public void setRefundType(RefundTypeEnum refundType) {
        this.refundType = refundType;
    }

    public Long getOperatorStaffId() {
        return operatorStaffId;
    }

    public void setOperatorStaffId(Long operatorStaffId) {
        this.operatorStaffId = operatorStaffId;
    }

    public Long getAuthorStaffId() {
        return authorStaffId;
    }

    public void setAuthorStaffId(Long authorStaffId) {
        this.authorStaffId = authorStaffId;
    }

    public RefundReasonTypeEnum getRefundReasonType() {
        return refundReasonType;
    }

    public void setRefundReasonType(RefundReasonTypeEnum refundReasonType) {
        this.refundReasonType = refundReasonType;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
    }

    public String getOperatorStaffName() {
        return operatorStaffName;
    }

    public void setOperatorStaffName(String operatorStaffName) {
        this.operatorStaffName = operatorStaffName;
    }

    public String getAuthorStaffName() {
        return authorStaffName;
    }

    public void setAuthorStaffName(String authorStaffName) {
        this.authorStaffName = authorStaffName;
    }

    public List<OrderRefundReasonItemVO> getOrderRefundReasonItems() {
        return orderRefundReasonItems;
    }

    public void setOrderRefundReasonItems(List<OrderRefundReasonItemVO> orderRefundReasonItems) {
        this.orderRefundReasonItems = orderRefundReasonItems;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }


    public String getOperatorStaffRoleName() {
        return operatorStaffRoleName;
    }

    public void setOperatorStaffRoleName(String operatorStaffRoleName) {
        this.operatorStaffRoleName = operatorStaffRoleName;
    }
}
