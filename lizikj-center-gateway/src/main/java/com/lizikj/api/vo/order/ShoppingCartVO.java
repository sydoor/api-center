package com.lizikj.api.vo.order;

import com.lizikj.api.vo.merchandise.CartGoodsVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/8/17 17:18
 */
@ApiModel
public class ShoppingCartVO {
    /**
     * 购物车物品列表
     */
    @ApiModelProperty(name = "shoppingCartGoodsVOList", value = "购物车物品列表")
    List<CartGoodsVO> shoppingCartGoodsList;
    /**
     * 自增ID
     */
    @ApiModelProperty(name = "shopingCartId", value = "购物车ID")
    private Long shoppingCartId;
    /**
     * 用户Id,H5购物车时有意义
     */
    @ApiModelProperty(name = "userId", value = "用户Id,H5购物车时有意义")
    private Long userId;
    /**
     * 店铺id(如果user_id不唯一，店铺id有意义,如果是POS购物车，shop_id也有意义)
     */
    @ApiModelProperty(name = "shopId", value = "店铺id(如果user_id不唯一，店铺id有意义,如果是POS购物车，shop_id也有意义)")
    private Long shopId;
    /**
     * 店铺桌子id,POS购物车同步到服务端时有意义上，目前没有意义
     */
    @ApiModelProperty(name = "areaDeskId", value = "店铺桌子id,POS购物车同步到服务端时有意义上，目前没有意义")
    private Long areaDeskId;
    /**
     * 购物车类型1 H5购物车，2POS购物车
     */
    @ApiModelProperty(name = "shoppingType", value = "购物车类型1 H5购物车，2POS购物车")
    private Byte shoppingType;

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getAreaDeskId() {
        return areaDeskId;
    }

    public void setAreaDeskId(Long areaDeskId) {
        this.areaDeskId = areaDeskId;
    }

    public Byte getShoppingType() {
        return shoppingType;
    }

    public void setShoppingType(Byte shoppingType) {
        this.shoppingType = shoppingType;
    }

    public List<CartGoodsVO> getShoppingCartGoodsList() {
        return shoppingCartGoodsList;
    }

    public void setShoppingCartGoodsList(List<CartGoodsVO> shoppingCartGoodsList) {
        this.shoppingCartGoodsList = shoppingCartGoodsList;
    }
}
