package com.lizikj.api.vo.order.param;

import com.lizikj.api.vo.order.detail.OrderPayInfoVO;
import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.order.enums.OrderTypeEnum;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuyuntao
 * Date: 17-7-11
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class OrderStatusChangeVO implements Serializable {
    private static final long serialVersionUID = 5900718385024069146L;//订单状态变更dto
    private Long orderId;//订单id
    private OrderStatusEnum oldOrderStatus;//旧的订单状态
    private OrderStatusEnum toOrderStatus;//订单目标状态
    private Long updateUserId;//修改人
    private String snNum;//snNum号
    private OrderPayInfoVO orderPayInfoDto;//订单支付信息

    private OrderTypeEnum orderTypeEnum;//订单类型

    private String remark;//备注信息


    public OrderTypeEnum getOrderTypeEnum() {
        return orderTypeEnum;
    }

    public void setOrderTypeEnum(OrderTypeEnum orderTypeEnum) {
        this.orderTypeEnum = orderTypeEnum;
    }

    public OrderStatusEnum getOldOrderStatus() {
        return oldOrderStatus;
    }

    public void setOldOrderStatus(OrderStatusEnum oldOrderStatus) {
        this.oldOrderStatus = oldOrderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderPayInfoVO getOrderPayInfoDto() {
        return orderPayInfoDto;
    }

    public void setOrderPayInfoDto(OrderPayInfoVO orderPayInfoDto) {
        this.orderPayInfoDto = orderPayInfoDto;
    }

    public OrderStatusEnum getToOrderStatus() {
        return toOrderStatus;
    }

    public void setToOrderStatus(OrderStatusEnum toOrderStatus) {
        this.toOrderStatus = toOrderStatus;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getSnNum() {
        return snNum;
    }

    public void setSnNum(String snNum) {
        this.snNum = snNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
