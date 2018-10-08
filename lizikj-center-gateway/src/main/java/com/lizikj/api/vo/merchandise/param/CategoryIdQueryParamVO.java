package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.YesOrNoEnum;

/**
 * @author dyh
 * @created at 2018 01 15 11:41
 */
public class CategoryIdQueryParamVO {

    private String categoryId;

    private YesOrNoEnum needComputeSpecialDiscount;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public YesOrNoEnum getNeedComputeSpecialDiscount() {
        return needComputeSpecialDiscount;
    }

    public void setNeedComputeSpecialDiscount(YesOrNoEnum needComputeSpecialDiscount) {
        this.needComputeSpecialDiscount = needComputeSpecialDiscount;
    }
}
