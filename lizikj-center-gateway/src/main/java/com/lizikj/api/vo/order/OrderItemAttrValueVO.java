package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michael.Huang
 * @date 2017/8/25 15:28
 */
@ApiModel
public class OrderItemAttrValueVO extends ItemAttrValueVO {

    @ApiModelProperty(value = "数据库的ID", required = true)
    private Long orderItemAttrValueId;

    public Long getOrderItemAttrValueId() {
        return orderItemAttrValueId;
    }

    public void setOrderItemAttrValueId(Long orderItemAttrValueId) {
        this.orderItemAttrValueId = orderItemAttrValueId;
    }


}
