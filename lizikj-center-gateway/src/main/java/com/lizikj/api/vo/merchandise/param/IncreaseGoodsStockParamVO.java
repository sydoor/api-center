package com.lizikj.api.vo.merchandise.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2017 09 13 16:21
 */
@ApiModel(value = "录入库存")
public class IncreaseGoodsStockParamVO {
    @ApiModelProperty(value = "商品id")
    private String goodsId;
    @ApiModelProperty(value = "skuid")
    private Long skuId;
    @ApiModelProperty(value = "库存")
    private Integer stock;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
