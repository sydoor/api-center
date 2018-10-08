package com.lizikj.api.vo.order.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 购物车物品
 * Created by adept on 2017/7/17.
 */
@ApiModel
public class CartGoodsParamVO extends ItemParamVO<CartGoodsAttrParamVO> {

    private static final long serialVersionUID = -2751396979616212944L;


    /**
     * 购物车物品ID,此字段更新购物车单个物品时有意义
     */
    @ApiModelProperty(value = "购物车物品ID,此字段更新购物车单个物品时有意义")
    private String cartGoodsId;

    /**
     * 区域桌号ID
     */
    @ApiModelProperty(value = "区域桌号ID，POS同步购物车时才有意义，H5不需要传")
    private Long areaDeskId;

    public Long getAreaDeskId() {
        return areaDeskId;
    }

    public void setAreaDeskId(Long areaDeskId) {
        this.areaDeskId = areaDeskId;
    }

    public String getCartGoodsId() {
        return cartGoodsId;
    }

    public void setCartGoodsId(String cartGoodsId) {
        this.cartGoodsId = cartGoodsId;
    }
}
