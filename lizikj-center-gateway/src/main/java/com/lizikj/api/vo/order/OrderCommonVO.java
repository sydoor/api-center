package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by adept on 2017/7/17.
 */
@ApiModel
public class OrderCommonVO {


    @ApiModelProperty(value = "订单id")
    private Long orderId;//订单id

    @ApiModelProperty(value = "订单号")
    private String orderNo;//订单号

    @ApiModelProperty(value = "是否成功")
    private Boolean succStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Boolean getSuccStatus() {
        return succStatus;
    }

    public void setSuccStatus(Boolean succStatus) {
        this.succStatus = succStatus;
    }
}
