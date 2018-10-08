package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.lizikj.order.enums.DiscountTypeNodeEnum;

/**
 * @author zhoufe
 * @date 2017/12/12 19:53
 */
@ApiModel
public class DiscountTypeVO implements Serializable {

    private static final long serialVersionUID = 4354526581765752150L;
    /**
     * 供选择的优惠
     */
    @ApiModelProperty(value = "供选择的优惠")
    private DiscountTypeNodeEnum discountSelected;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    private Long needAmount;

    /**
     * 优惠总金额
     */
    @ApiModelProperty(value = "优惠总金额")
    private Long benefitAmount;

    public DiscountTypeNodeEnum getDiscountSelected() {
        return discountSelected;
    }

    public void setDiscountSelected(DiscountTypeNodeEnum discountSelected) {
        this.discountSelected = discountSelected;
    }

    public Long getNeedAmount() {
        return needAmount;
    }

    public void setNeedAmount(Long needAmount) {
        this.needAmount = needAmount;
    }

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }
}