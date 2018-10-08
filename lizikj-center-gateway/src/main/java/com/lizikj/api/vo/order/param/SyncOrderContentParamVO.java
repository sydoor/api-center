package com.lizikj.api.vo.order.param;

import com.lizikj.api.vo.order.*;
import com.lizikj.order.dto.OrderDiscountDTO;
import com.lizikj.order.dto.OrderItemDTO;
import com.lizikj.order.dto.detail.OrderRefundReasonInfoDTO;
import com.lizikj.payment.pay.dto.PayFlowDTO;
import com.lizikj.payment.refund.dto.RefundOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhoufe
 * @date 2017/9/25 20:34
 */
@ApiModel
public class SyncOrderContentParamVO implements Serializable {

    private static final long serialVersionUID = -4087286548198254440L;


    //提交的
    @ApiModelProperty(value = "优惠信息")
    private List<OrderDiscountVO> orderDiscounts;

    @ApiModelProperty(value = "支付信息")
    private List<SyncPosPayFlowVO> payFlows;

    @ApiModelProperty(value = "退款流水信息")
    private List<SyncPosRefundOrderVO> refundOrders;

    //查询的
    @ApiModelProperty(value = "订单商品信息")
    private List<OrderItemVO> orderItems;

    @ApiModelProperty(value = "退款原因信息")
    private List<OrderRefundReasonInfoVO> orderRefundReasonInfos;


    public List<SyncPosPayFlowVO> getPayFlows() {
        return payFlows;
    }

    public void setPayFlows(List<SyncPosPayFlowVO> payFlows) {
        this.payFlows = payFlows;
    }

    public List<SyncPosRefundOrderVO> getRefundOrders() {
        return refundOrders;
    }

    public void setRefundOrders(List<SyncPosRefundOrderVO> refundOrders) {
        this.refundOrders = refundOrders;
    }

    public List<OrderItemVO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemVO> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderRefundReasonInfoVO> getOrderRefundReasonInfos() {
        return orderRefundReasonInfos;
    }

    public void setOrderRefundReasonInfos(List<OrderRefundReasonInfoVO> orderRefundReasonInfos) {
        this.orderRefundReasonInfos = orderRefundReasonInfos;
    }

    public List<OrderDiscountVO> getOrderDiscounts() {
        return orderDiscounts;
    }

    public void setOrderDiscounts(List<OrderDiscountVO> orderDiscounts) {
        this.orderDiscounts = orderDiscounts;
    }
}
