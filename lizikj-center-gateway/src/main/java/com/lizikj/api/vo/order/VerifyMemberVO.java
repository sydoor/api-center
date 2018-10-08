package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 结账返回的结果
 * @author zhoufe
 * @date 2017/8/11 17:03
 */
@ApiModel
public class VerifyMemberVO extends OrderCommonVO {

    private static final long serialVersionUID = -6694005676576123303L;

    @ApiModelProperty(value = "会员等级")
    private String levelName;

    @ApiModelProperty(value = "等级对应的折扣")
    private Integer discount;

    @ApiModelProperty(value = "实收金额")
    private Long needAmount;

    @ApiModelProperty(value = "订单金额")
    private Long totalAmount;

    @ApiModelProperty(value = "会员优惠金额")
    private Long benefitAmount;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Long getNeedAmount() {
        return needAmount;
    }

    public void setNeedAmount(Long needAmount) {
        this.needAmount = needAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(Long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }
}
