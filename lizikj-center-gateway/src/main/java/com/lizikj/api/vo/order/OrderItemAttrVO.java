package com.lizikj.api.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/8/25 15:29
 */
@ApiModel
public class OrderItemAttrVO extends ItemAttrVO {

    @ApiModelProperty(value = "数据库记录ID", required = true)
    private Long orderItemAttrId;
    @ApiModelProperty(value = "商品属性值", required = true)
    private List<OrderItemAttrValueVO> itemAttrValueList;

    public Long getOrderItemAttrId() {
        return orderItemAttrId;
    }

    public void setOrderItemAttrId(Long orderItemAttrId) {
        this.orderItemAttrId = orderItemAttrId;
    }

    public List<OrderItemAttrValueVO> getItemAttrValueList() {
        return itemAttrValueList;
    }

    public void setItemAttrValueList(List<OrderItemAttrValueVO> itemAttrValueList) {
        this.itemAttrValueList = itemAttrValueList;
    }
}
