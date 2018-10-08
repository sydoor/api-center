package com.lizikj.api.vo.order.detail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 订单下单人信息
 *
 * @author Michael.Huang
 * @date 2017/8/26 11:41
 */
@ApiModel
public class OrderPersonInfoVO implements Serializable {
    /**
     * 下单员ID
     */
    @ApiModelProperty(value = "下单员ID")
    private Long orderPersonId;

    /**
     * 下单员名称
     */
    @ApiModelProperty(value = "下单员名称")
    private String orderPersonName;

    /**
     * 下单员角色
     */
    @ApiModelProperty(value = "下单员角色")
    private String orderPersonRoleName;

    public Long getOrderPersonId() {
        return orderPersonId;
    }

    public void setOrderPersonId(Long orderPersonId) {
        this.orderPersonId = orderPersonId;
    }

    public String getOrderPersonRoleName() {
        return orderPersonRoleName;
    }

    public void setOrderPersonRoleName(String orderPersonRoleName) {
        this.orderPersonRoleName = orderPersonRoleName;
    }

    public String getOrderPersonName() {
        return orderPersonName;
    }

    public void setOrderPersonName(String orderPersonName) {
        this.orderPersonName = orderPersonName;
    }
}
