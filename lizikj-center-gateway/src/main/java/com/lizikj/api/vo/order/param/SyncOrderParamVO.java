package com.lizikj.api.vo.order.param;

import com.lizikj.order.enums.*;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.refund.enums.RefundStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 已废弃（用了OrderInfoVO）
 *
 * 同步订单的参数
 * @author zhoufe
 * @date 2017/8/11 17:03
 */
@Deprecated
@ApiModel
public class SyncOrderParamVO extends OrderInfoParamVO{

    private static final long serialVersionUID = -6694005676576123303L;

    @ApiModelProperty(value = "POS产生订单号：SD+时间戳+8位随机数+sn的hash值")
    private String orderNo;

    @ApiModelProperty(value = "POS订单时的类型：见：OrderTypeEnum：NORMAL.普通订单，EAT_FIRST.先食后付款，PAY_FIRST.先付后食。", required = true)
    private OrderTypeEnum orderType;

    @ApiModelProperty(value = "POS订单时的订单状态：见：OrderStatusEnum：WAIT_REC.待接单,WAIT_PAY.待结账,FINISHED.已完成,REFUND.退款,CANCEL.取消订单。", required = true)
    private OrderStatusEnum orderStatus;

    @ApiModelProperty(value = "订单业务类型：见OrderSourceEnum：MERCHANDISE.美食订单，MONEY.快捷收银订单，MEMBER_RECHARGE.会员充值，VALUE_ADD.增值服务。", required = true)
    private OrderBizTypeEnum orderBizType;

    @ApiModelProperty(value = "POS订单的支付，优惠，退款，退款及原因", required = true)
    private SyncOrderContentParamVO syncOrderContent;

    @ApiModelProperty(value = "下单人ID：登录POS的操作人", required = true)
    private Long orderPersonId;

    @ApiModelProperty(value = "下单时间", required = true)
    private Date orderTime;

    @ApiModelProperty(value = "接单时间", required = true)
    private Date recTime;

    @ApiModelProperty(value = "订单关闭状态：见：CloseStatusEnum：OPENED.开启，CLOSED.关闭。", required = true)
    private CloseStatusEnum closeStatus;

    @ApiModelProperty(value = "开启关闭：见：InvoiceStatusEnum：YES.是,NO.否。", required = true)
    private InvoiceStatusEnum invoiceStatus;

    @ApiModelProperty(value = "订单备注")
    private String remark;

    @ApiModelProperty(value = "会员手机号")
    private String mobile;

    @ApiModelProperty(value = "商户ID", required = true)
    private Long merchantId;

    @ApiModelProperty(value = "订单总金额", required = true)
    private Long totalAmount;

    @ApiModelProperty(value = "应付金额", required = true)
    private Long needAmount;

    @ApiModelProperty(value = "成本总金额", required = true)
    private Long costAmount;

    @ApiModelProperty(value = "优惠金额")
    private Long benefitAmount;

    @ApiModelProperty(value = "支付金额：回调的金额")
    private Long payAmount;

    @ApiModelProperty(value = "退款金额")
    private Long refundAmount;

    @ApiModelProperty(value = "现金支付是接收到的金额")
    private Long receiveCashAmount;

    @ApiModelProperty(value = "现金支付是接收到的金额的找零")
    private Long cashChangeAmount;

    @ApiModelProperty(value = "订单排序号", required = true)
    private String sort;

    @ApiModelProperty(value = "订单排序号", required = true)
    private PayStatusEnum payStatus;

    @ApiModelProperty(value = "退款状态：见RefundStatusEnum：NORMAL.默认状态，APPLY.退款申请，SUCCESS.退款成功，FAIL.退款失败。", required = true)
    private RefundStatusEnum refundStatus;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public SyncOrderContentParamVO getSyncOrderContent() {
        return syncOrderContent;
    }

    public void setSyncOrderContent(SyncOrderContentParamVO syncOrderContent) {
        this.syncOrderContent = syncOrderContent;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderPersonId() {
        return orderPersonId;
    }

    public void setOrderPersonId(Long orderPersonId) {
        this.orderPersonId = orderPersonId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

    public CloseStatusEnum getCloseStatus() {
        return closeStatus;
    }

    public void setCloseStatus(CloseStatusEnum closeStatus) {
        this.closeStatus = closeStatus;
    }

    public InvoiceStatusEnum getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatusEnum invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getNeedAmount() {
        return needAmount;
    }

    public void setNeedAmount(Long needAmount) {
        this.needAmount = needAmount;
    }

    public Long getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Long costAmount) {
        this.costAmount = costAmount;
    }

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Long getReceiveCashAmount() {
        return receiveCashAmount;
    }

    public void setReceiveCashAmount(Long receiveCashAmount) {
        this.receiveCashAmount = receiveCashAmount;
    }

    public Long getCashChangeAmount() {
        return cashChangeAmount;
    }

    public void setCashChangeAmount(Long cashChangeAmount) {
        this.cashChangeAmount = cashChangeAmount;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public PayStatusEnum getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatusEnum payStatus) {
        this.payStatus = payStatus;
    }

    public RefundStatusEnum getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatusEnum refundStatus) {
        this.refundStatus = refundStatus;
    }

    public OrderBizTypeEnum getOrderBizType() {
        return orderBizType;
    }

    public void setOrderBizType(OrderBizTypeEnum orderBizType) {
        this.orderBizType = orderBizType;
    }
}
