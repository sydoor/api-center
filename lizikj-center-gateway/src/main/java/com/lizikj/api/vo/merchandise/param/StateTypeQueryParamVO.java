package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.StateTypeEnum;
import com.lizikj.merchandise.enums.YesOrNoEnum;

/**
 * @author dyh
 * @created at 2018 01 15 11:20
 */
public class StateTypeQueryParamVO {

    private StateTypeEnum stateType;

    private YesOrNoEnum needComputeSpecialDiscount;

    public StateTypeEnum getStateType() {
        return stateType;
    }

    public void setStateType(StateTypeEnum stateType) {
        this.stateType = stateType;
    }

    public YesOrNoEnum getNeedComputeSpecialDiscount() {
        return needComputeSpecialDiscount;
    }

    public void setNeedComputeSpecialDiscount(YesOrNoEnum needComputeSpecialDiscount) {
        this.needComputeSpecialDiscount = needComputeSpecialDiscount;
    }

}

