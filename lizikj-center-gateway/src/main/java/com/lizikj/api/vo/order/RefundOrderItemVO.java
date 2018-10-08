package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2017/9/28 15:39
 */
@ApiModel
public class RefundOrderItemVO implements Serializable {

    private static final long serialVersionUID = 2192811359977656476L;

    /**
     * 商品id(goodsType=1，则是商品id，goodsType=2，则是增值服务Id)
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private String goodsId;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量", required = true)
    private long goodsNum;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(long goodsNum) {
        this.goodsNum = goodsNum;
    }

}
