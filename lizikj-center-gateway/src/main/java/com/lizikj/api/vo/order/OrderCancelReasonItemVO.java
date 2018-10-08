package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 取消订单的物品
 * @auth zone
 * @date 2018-01-19
 */
@ApiModel
public class OrderCancelReasonItemVO {
    private static final long serialVersionUID = -4723011678491469679L;

    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @ApiModelProperty(value = "订单号", required = true)
    private String orderNo;

    @ApiModelProperty(value = "订单商品ID", required = true)
    private Long orderItemId;

    @ApiModelProperty(value = "美食ID", required = true)
    private String goodsId;

    @ApiModelProperty(value = "订单商品名称", required = true)
    private String goodsName;

    @ApiModelProperty(value = "问题描述", required = true)
    private String orderGoodsProblem;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderGoodsProblem() {
        return orderGoodsProblem;
    }

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