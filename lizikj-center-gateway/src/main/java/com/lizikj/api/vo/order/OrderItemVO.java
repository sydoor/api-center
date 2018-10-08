package com.lizikj.api.vo.order;

import com.lizikj.order.enums.CutPriceFlagEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/8/25 15:34
 */
@ApiModel
public class OrderItemVO extends ItemVO implements Serializable {

    private static final long serialVersionUID = 1176664103611776822L;

    @ApiModelProperty(value = "订单商品ID", required = true)
    private Long orderItemId;

    /**
     * 砍价标识
     */
    @ApiModelProperty(value = "砍价标识，UNABLE：不能砍价，ENABLE：允许砍价，DOING：砍价进行中", required = true)
    private CutPriceFlagEnum cutPriceFlag;

    /**
     * 下单版本(第一次下单版本号和OrderInfo#createTime一致）如果不一致说明是追加菜
     */
    @ApiModelProperty(value = "下单版本(第一次下单版本号和OrderInfo#createTime一致）如果不一致说明是追加菜", required = true)
    private Long orderVersion;

    @ApiModelProperty(value = "商品属性", required = true)
    private List<OrderItemAttrVO> itemAttrList;

    public List<OrderItemAttrVO> getItemAttrList() {
        return itemAttrList;
    }

    public void setItemAttrList(List<OrderItemAttrVO> itemAttrList) {
        this.itemAttrList = itemAttrList;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderVersion() {
        return orderVersion;
    }

    public void setOrderVersion(Long orderVersion) {
        this.orderVersion = orderVersion;
    }

    public CutPriceFlagEnum getCutPriceFlag() {
        return cutPriceFlag;
    }

    public void setCutPriceFlag(CutPriceFlagEnum cutPriceFlag) {
        this.cutPriceFlag = cutPriceFlag;
    }
}
