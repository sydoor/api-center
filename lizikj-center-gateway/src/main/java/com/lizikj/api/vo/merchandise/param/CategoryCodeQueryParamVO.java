package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.CategoryCodeEnum;
import com.lizikj.merchandise.enums.YesOrNoEnum;

/**
 * @author dyh
 * @created at 2018 01 15 11:44
 */
public class CategoryCodeQueryParamVO {

    private CategoryCodeEnum code;

    private YesOrNoEnum needComputeSpecialDiscount;
    
    public CategoryCodeEnum getCode() {
        return code;
    }

    public void setCode(CategoryCodeEnum code) {
        this.code = code;
    }

    public YesOrNoEnum getNeedComputeSpecialDiscount() {
        return needComputeSpecialDiscount;
    }

    public void setNeedComputeSpecialDiscount(YesOrNoEnum needComputeSpecialDiscount) {
        this.needComputeSpecialDiscount = needComputeSpecialDiscount;
    }
}
