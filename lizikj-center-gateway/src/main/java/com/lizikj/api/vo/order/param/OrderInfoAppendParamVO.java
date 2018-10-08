package com.lizikj.api.vo.order.param;

import com.lizikj.order.enums.PackStatusEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 加减菜
 * Created by adept on 2017/7/13.
 */
@ApiModel
@Deprecated
public class OrderInfoAppendParamVO extends BaseDTO {

    private static final long serialVersionUID = -7227779603837330132L;

    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;


    @ApiModelProperty(value = "减菜：订单商品的ID", required = true)
    private List<Long> removeOrderItemIds;

    @ApiModelProperty(value="就餐人数：可能加人，减人")
    private Long peoples;

    @ApiModelProperty(value="整个单的备注：追加备注")
    private String remark;

    @ApiModelProperty(value="是否打包整个单")
    private PackStatusEnum packStatus;

    @ApiModelProperty(value = "加菜/赠菜/改菜", required = true)
    private List<OrderItemParamVO> itemParamList;


    public Long getPeoples() {
        return peoples;
    }

    public void setPeoples(Long peoples) {
        this.peoples = peoples;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public PackStatusEnum getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(PackStatusEnum packStatus) {
        this.packStatus = packStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Long> getRemoveOrderItemIds() {
        return removeOrderItemIds;
    }

    public void setRemoveOrderItemIds(List<Long> removeOrderItemIds) {
        this.removeOrderItemIds = removeOrderItemIds;
    }

    public List<OrderItemParamVO> getItemParamList() {
        return itemParamList;
    }

    public void setItemParamList(List<OrderItemParamVO> itemParamList) {
        this.itemParamList = itemParamList;
    }
}
