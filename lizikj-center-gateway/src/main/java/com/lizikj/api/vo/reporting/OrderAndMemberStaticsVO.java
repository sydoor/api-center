package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/8/3 17:09
 */
@ApiModel(value = "订单和会员统计")
public class OrderAndMemberStaticsVO {
    @ApiModelProperty(value = "营业额")
    private Long totalAmount;
    @ApiModelProperty(value = "实收金额")
    private Long receiveAmount;
    @ApiModelProperty(value = "订单数")
    private Integer orderNum;
    @ApiModelProperty(value = "商户会员总数")
    private Integer totalMerchantMemberNum;
    @ApiModelProperty(value = "新增商户会员")
    private Integer increaseMerchantMemberNum;

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(Long receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getTotalMerchantMemberNum() {
        return totalMerchantMemberNum;
    }

    public void setTotalMerchantMemberNum(Integer totalMerchantMemberNum) {
        this.totalMerchantMemberNum = totalMerchantMemberNum;
    }

    public Integer getIncreaseMerchantMemberNum() {
        return increaseMerchantMemberNum;
    }

    public void setIncreaseMerchantMemberNum(Integer increaseMerchantMemberNum) {
        this.increaseMerchantMemberNum = increaseMerchantMemberNum;
    }
}
