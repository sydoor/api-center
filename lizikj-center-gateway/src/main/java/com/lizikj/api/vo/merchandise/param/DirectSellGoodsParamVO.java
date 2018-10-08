package com.lizikj.api.vo.merchandise.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2017 09 14 15:51
 */
@ApiModel(value = "直接售卖")
public class DirectSellGoodsParamVO {
    @ApiModelProperty(value = "商品id")
    private String goodsId;
    @ApiModelProperty(value = "skuId")
    private Integer skuId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
}
