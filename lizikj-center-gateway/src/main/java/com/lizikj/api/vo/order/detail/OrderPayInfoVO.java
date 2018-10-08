package com.lizikj.api.vo.order.detail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/8/22 15:05
 */
@ApiModel
public class OrderPayInfoVO {
    /**
     * 支付流水列表
     */
    @ApiModelProperty(value = "支付流水列表")
    List<OrderPayFlowVO> payFlowList;
    /**
     * snNum号
     */
    @ApiModelProperty(value = "snNum号")
    private String snNum;
    /**
     * 订单总金额
     */
    @ApiModelProperty(value = "订单总金额")
    private Long totalAmount;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private Long benefitAmount;
    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private Long payAmount;

    public String getSnNum() {
        return snNum;
    }

    public void setSnNum(String snNum) {
        this.snNum = snNum;
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

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public List<OrderPayFlowVO> getPayFlowList() {
        return payFlowList;
    }

    public void setPayFlowList(List<OrderPayFlowVO> payFlowList) {
        this.payFlowList = payFlowList;
    }
}
