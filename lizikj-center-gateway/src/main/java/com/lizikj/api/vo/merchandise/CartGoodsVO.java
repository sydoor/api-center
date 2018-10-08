package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 * 购物车物品
 *
 * @author Michael.Huang
 * @date 2017/8/28 15:46
 */
@ApiModel
public class CartGoodsVO extends GoodsVO {
    /**
     * 商户用户id
     */
    @ApiModelProperty(value = "商户用户id")
    private Long merchantUserId;

    /**
     * 桌号id（支持POS购物车)
     */
    @ApiModelProperty(value = "桌号id（支持POS购物车),POS同步上来时有意义")
    private Long areaDeskId;

    /**
     * 购物车ID
     */
    @ApiModelProperty(value = "购物车ID")
    private Long shoppingCartId;

    /**
     * 物品id
     */
    @ApiModelProperty(value = "物品id")
    private String goodsId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 口味偏好：下单的商品的口味偏好记录到这里
     */
    @ApiModelProperty(value = "是否赠品")
    private Set<String> flavor;

    /**
     * 是否赠品
     */
    @ApiModelProperty(value = "是否赠品")
    private Boolean freeDishStatus;

    /**
     * 是否打包该美食
     */
    @ApiModelProperty(value = "是否打包该美食")
    private Boolean packStatus;

    public Long getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(Long merchantUserId) {
        this.merchantUserId = merchantUserId;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<String> getFlavor() {
        return flavor;
    }

    public void setFlavor(Set<String> flavor) {
        this.flavor = flavor;
    }

    public Boolean getFreeDishStatus() {
        return freeDishStatus;
    }

    public void setFreeDishStatus(Boolean freeDishStatus) {
        this.freeDishStatus = freeDishStatus;
    }

    public Boolean getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(Boolean packStatus) {
        this.packStatus = packStatus;
    }

    public Long getAreaDeskId() {
        return areaDeskId;
    }

    public void setAreaDeskId(Long areaDeskId) {
        this.areaDeskId = areaDeskId;
    }
}
