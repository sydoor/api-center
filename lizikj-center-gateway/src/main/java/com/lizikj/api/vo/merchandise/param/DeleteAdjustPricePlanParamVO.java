package com.lizikj.api.vo.merchandise.param;

import java.util.List;

/**
 * @author dyh
 * @created at 2018 01 19 14:40
 */
public class DeleteAdjustPricePlanParamVO {

    private String adjustPricePlanName;

    private List<String> goodsIds;

    public String getAdjustPricePlanName() {
        return adjustPricePlanName;
    }

    public void setAdjustPricePlanName(String adjustPricePlanName) {
        this.adjustPricePlanName = adjustPricePlanName;
    }

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }
}
