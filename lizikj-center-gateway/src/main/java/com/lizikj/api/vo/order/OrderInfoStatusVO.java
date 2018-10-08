package com.lizikj.api.vo.order;

import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.payment.pay.enums.PayStatusEnum;
import com.lizikj.payment.refund.enums.RefundStatusEnum;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2017/11/3 15:46
 */
public class OrderInfoStatusVO implements Serializable {
    private static final long serialVersionUID = 4694975070577054811L;

    private OrderStatusEnum orderStatus;
    private PayStatusEnum payStatus;
    private RefundStatusEnum refundStatus;

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
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
}
