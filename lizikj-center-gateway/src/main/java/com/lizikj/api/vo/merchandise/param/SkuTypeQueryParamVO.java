package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.SkuTypeEnum;
import com.lizikj.merchandise.enums.YesOrNoEnum;

/**
 * @author dyh
 * @created at 2018 01 15 11:13
 */
public class SkuTypeQueryParamVO {
    private SkuTypeEnum skuType;

    private YesOrNoEnum needComputeSpecialDiscount;

    public SkuTypeEnum getSkuType() {
        return skuType;
    }

    public void setSkuType(SkuTypeEnum skuType) {
        this.skuType = skuType;
    }

    public YesOrNoEnum getNeedComputeSpecialDiscount() {
        return needComputeSpecialDiscount;
    }

    public void setNeedComputeSpecialDiscount(YesOrNoEnum needComputeSpecialDiscount) {
        this.needComputeSpecialDiscount = needComputeSpecialDiscount;
    }
}

