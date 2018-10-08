package com.lizikj.api.vo.merchandise.param;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2017 08 29 10:32
 */
@ApiModel
public class GoodsStateParamVO extends BaseDTO {

    @ApiModelProperty(value = "美食ID", required = true)
    private String goodsId;

    @ApiModelProperty(value = "Sku Id")
    private long skuId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }
}
