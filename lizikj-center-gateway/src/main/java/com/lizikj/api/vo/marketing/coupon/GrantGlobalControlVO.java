package com.lizikj.api.vo.marketing.coupon;

import com.lizikj.marketing.api.enums.ControlTypeEnum;
import com.lizikj.marketing.api.enums.PrizeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/8/29 15:55
 */
@ApiModel
public class GrantGlobalControlVO {
    @ApiModelProperty(value = "控制规则ID")
    private Long id;
    @ApiModelProperty(value = "控制对象类型")
    private PrizeTypeEnum prizeType;
    @ApiModelProperty(value = "控制类型")
    private ControlTypeEnum controlType;
    @ApiModelProperty(value = "控制值")
    private Long controlValue;
    @ApiModelProperty(value = "事实")
    private Long fact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrizeTypeEnum getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(PrizeTypeEnum prizeType) {
        this.prizeType = prizeType;
    }

    public ControlTypeEnum getControlType() {
        return controlType;
    }

    public void setControlType(ControlTypeEnum controlType) {
        this.controlType = controlType;
    }

    public Long getControlValue() {
        return controlValue;
    }

    public void setControlValue(Long controlValue) {
        this.controlValue = controlValue;
    }

    public Long getFact() {
        return fact;
    }

    public void setFact(Long fact) {
        this.fact = fact;
    }
}
