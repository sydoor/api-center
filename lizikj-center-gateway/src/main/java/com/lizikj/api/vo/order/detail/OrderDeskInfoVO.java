package com.lizikj.api.vo.order.detail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单餐桌信息
 *
 * @author Michael.Huang
 * @date 2017/8/26 11:40
 */
@ApiModel
public class OrderDeskInfoVO {
    /**
     * 餐桌id
     */
    @ApiModelProperty(value = "餐桌id")
    private Long areaDeskId;

    /**
     * 餐桌名称
     */
    @ApiModelProperty(value = "餐桌名称")
    private String areaDeskName;

    /**
     * 自定义桌牌号：桌台管理关闭，自定义桌牌号开启时从前端传入
     */
    @ApiModelProperty(value = "自定义桌牌号")
    private String customDeskNumber;

    /**
     * 订单序号
     */
    @ApiModelProperty(value = "订单序号")
    private String orderSort;


    public Long getAreaDeskId() {
        return areaDeskId;
    }

    public void setAreaDeskId(Long areaDeskId) {
        this.areaDeskId = areaDeskId;
    }

    public String getAreaDeskName() {
        return areaDeskName;
    }

    public void setAreaDeskName(String areaDeskName) {
        this.areaDeskName = areaDeskName;
    }

    public String getCustomDeskNumber() {
        return customDeskNumber;
    }

    public void setCustomDeskNumber(String customDeskNumber) {
        this.customDeskNumber = customDeskNumber;
    }

    public String getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(String orderSort) {
        this.orderSort = orderSort;
    }
}
