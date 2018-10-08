package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- order_refund_item
 * Created by zhoufe on 2017-8-10 15:19:15
 */
@ApiModel
public class OrderRefundReasonItemVO {

    private static final long serialVersionUID = -4723011678491469679L;

    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @ApiModelProperty(value = "订单号", required = true)
    private String orderNo;

    /**
     * 订单商品ID
     */
    @ApiModelProperty(value = "订单商品ID", required = true)
    private Long orderItemId;

    /**
     * 美食ID
     */
    @ApiModelProperty(value = "美食ID", required = true)
    private String goodsId;

    /**
     * 订单商品名称
     */
    @ApiModelProperty(value = "订单商品名称", required = true)
    private String goodsName;

    /**
     * 问题描述
     */
    @ApiModelProperty(value = "问题描述", required = true)
    private String orderGoodsProblem;

    /**
     * 订单商品ID
     */
    public Long getOrderItemId() {
        return orderItemId;
    }

    /**
     * 订单商品ID
     *
     * @param orderItemId the value for order_refund_item.order_item_id
     */
    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    /**
     * 问题描述
     */
    public String getOrderGoodsProblem() {
        return orderGoodsProblem;
    }

    /**
     * 问题描述
     *
     * @param orderGoodsProblem the value for order_refund_item.order_goods_problem
     */
    public void setOrderGoodsProblem(String orderGoodsProblem) {
        this.orderGoodsProblem = orderGoodsProblem;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}