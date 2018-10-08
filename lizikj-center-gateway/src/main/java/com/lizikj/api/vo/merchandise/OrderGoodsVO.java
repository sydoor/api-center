package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 * 订单物品详情
 * @author Michael.Huang
 * @date 2017/8/28 15:47
 */
@ApiModel
public class OrderGoodsVO extends GoodsVO {

    /**
     * 订单id
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private Long orderId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private String orderNo;

    /**
     * 订单详情id
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private Long orderDetailId;

    /**
     * 物品id
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private String goodsId;

    /**
     * 口味偏好：下单的商品的口味偏好记录到这里
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private Set<String> flavor;

    /**
     * 备注：下单的商品的备注记录到这里
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private String remark;

    /**
     * 是否赠品
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private Boolean freeDishStatus;

    /**
     * 是否打包该美食
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private Boolean packStatus;

    /**
     * 是否临时菜
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private Boolean tempStatus;

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

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Set<String> getFlavor() {
        return flavor;
    }

    public void setFlavor(Set<String> flavor) {
        this.flavor = flavor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getFreeDishStatus() {
        return freeDishStatus;
    }

    public void setFreeDishStatus(Boolean freeDishStatus) {
        this.freeDishStatus = freeDishStatus;
    }

    public Boolean getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(Boolean packStatus) {
        this.packStatus = packStatus;
    }

    public Boolean getTempStatus() {
        return tempStatus;
    }

    public void setTempStatus(Boolean tempStatus) {
        this.tempStatus = tempStatus;
    }
}
