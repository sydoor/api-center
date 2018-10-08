package com.lizikj.api.vo.order.param;

import java.io.Serializable;

/**
 * Created by adept on 2017/7/13.
 */
public class OrderTableChangeVO implements Serializable {
    private static final long serialVersionUID = 3161767897845135926L;
    private Long orderId;//订单id
    private Long toTableId;//餐桌id
    private String remark;//备注

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getToTableId() {
        return toTableId;
    }

    public void setToTableId(Long toTableId) {
        this.toTableId = toTableId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
