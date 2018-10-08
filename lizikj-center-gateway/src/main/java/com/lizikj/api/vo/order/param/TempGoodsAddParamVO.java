package com.lizikj.api.vo.order.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhoufe
 * @date 2017/8/2 11:15
 */
@ApiModel
public class TempGoodsAddParamVO implements Serializable {

    private static final long serialVersionUID = -231902992390980078L;

    @ApiModelProperty(value = "菜名")
    private String goodsName;

    @ApiModelProperty(value = "价格：如果输入为0，表示即是赠菜，也是临时菜")
    private Long price;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
