package com.lizikj.api.vo.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2017/9/6 15:26
 */
@ApiModel
public class ShopCustomDeskNumberVO implements Serializable {

    private static final long serialVersionUID = -394461504177033032L;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "自定义桌牌号")
    private String customDeskNumber;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomDeskNumber() {
        return customDeskNumber;
    }

    public void setCustomDeskNumber(String customDeskNumber) {
        this.customDeskNumber = customDeskNumber;
    }
}
