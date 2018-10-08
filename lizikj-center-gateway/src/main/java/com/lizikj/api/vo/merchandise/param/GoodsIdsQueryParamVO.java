package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.YesOrNoEnum;

import java.util.List;

/**
 * @author dyh
 * @created at 2018 01 15 10:53
 */
public class GoodsIdsQueryParamVO {

    private List<String> goodsIds;

    private YesOrNoEnum needComputeSpecialDiscount;

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }

    public YesOrNoEnum getNeedComputeSpecialDiscount() {
        return needComputeSpecialDiscount;
    }

    public void setNeedComputeSpecialDiscount(YesOrNoEnum needComputeSpecialDiscount) {
        this.needComputeSpecialDiscount = needComputeSpecialDiscount;
    }
}