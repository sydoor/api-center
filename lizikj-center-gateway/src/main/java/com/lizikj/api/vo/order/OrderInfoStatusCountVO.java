package com.lizikj.api.vo.order;

import com.lizikj.order.enums.OrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2017/11/4 11:56
 */
@ApiModel
public class OrderInfoStatusCountVO implements Serializable {
    private static final long serialVersionUID = -4055435850185138566L;

    @ApiModelProperty(value = "订单状态：见：OrderStatusEnum：")
    private OrderStatusEnum orderStatus;

    @ApiModelProperty(value = "数量")
    private Long total;

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
