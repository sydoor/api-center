package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.YesOrNoEnum;

/**
 * @author dyh
 * @created at 2018 01 15 11:22
 */
public class GoodsIdQueryParamVO {
    private String goodsId;
    private YesOrNoEnum needComputeSpecialDiscount;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public YesOrNoEnum getNeedComputeSpecialDiscount() {
        return needComputeSpecialDiscount;
    }

    public void setNeedComputeSpecialDiscount(YesOrNoEnum needComputeSpecialDiscount) {
        this.needComputeSpecialDiscount = needComputeSpecialDiscount;
    }
}
