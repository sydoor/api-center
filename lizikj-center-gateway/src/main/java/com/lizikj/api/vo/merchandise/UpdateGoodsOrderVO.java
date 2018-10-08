package com.lizikj.api.vo.merchandise;

/**
 * @author dyh
 * @created at 2017 09 11 15:07
 */
public class UpdateGoodsOrderVO {
    private String goodsId;
    private Integer order;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
