package com.lizikj.api.vo.order.param;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ChangeTableParamVO  extends BaseDTO {

    private static final long serialVersionUID = -2167922087788095399L;
    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @ApiModelProperty(value = "目标桌号", required = true)
    private Integer toTableId;

    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setToTableId(Integer toTableId) {
        this.toTableId = toTableId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getToTableId() {
        return toTableId;
    }

    public String getRemark() {
        return remark;
    }
}
