package com.lizikj.api.vo.order.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 下单：接受页面参数
 * Created by adept on 2017/7/17.
 */
@ApiModel
public class OrderItemParamVO extends ItemParamVO<OrderItemAttrParamVO> implements Serializable {

    private static final long serialVersionUID = -6933900755581668306L;

    @ApiModelProperty(value = "订单商品ID：加菜不填此项，改菜要填", required = true)
    private Long orderItemId;

    /**
     * 购物车物品ID(如果商品来自购物车，下单此商品后，会清理购物车内容)
     */
    @ApiModelProperty(value = "购物车物品ID(如果商品来自购物车，下单此商品后，会清理购物车内容)")
    private String cartGoodsId;

    public String getCartGoodsId() {
        return cartGoodsId;
    }

    public void setCartGoodsId(String cartGoodsId) {
        this.cartGoodsId = cartGoodsId;
    }


    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }
}
