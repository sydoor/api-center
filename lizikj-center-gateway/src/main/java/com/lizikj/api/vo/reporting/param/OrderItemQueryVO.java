package com.lizikj.api.vo.reporting.param;

import com.lizikj.api.utils.ReportUtil;
import com.lizikj.marketing.api.enums.SupportCouponTimeTypeEnum;
import com.lizikj.order.enums.ItemTypeEnum;
import com.lizikj.order.enums.OrderSourceEnum;
import com.lizikj.order.enums.OrderStatusEnum;
import com.lizikj.reporting.dto.param.OrderItemQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @Date 2018/9/5 16:51
 */
@ApiModel
public class OrderItemQueryVO {
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "是否闲时")
    private SupportCouponTimeTypeEnum idleStatus;
    @ApiModelProperty(value = "订单项目类型")
    private ItemTypeEnum itemType;
    @ApiModelProperty(value = "订单项目类型")
    private List<ItemTypeEnum> itemTypes;
    @ApiModelProperty(value = "订单状态")
    private OrderStatusEnum orderStatus;
    @ApiModelProperty(value = "订单状态列表")
    private List<OrderStatusEnum> orderStatusList;
    @ApiModelProperty(value = "订单来源")
    private OrderSourceEnum orderSource;
    @ApiModelProperty(value = "订单来源列表")
    private List<OrderSourceEnum> orderSources;
    @ApiModelProperty(value = "topN")
    private Integer topN;


    public OrderItemQueryDTO convertToDTO(){
        OrderItemQueryDTO queryDTO = new OrderItemQueryDTO();
        queryDTO.setEndTime(endTime);
        queryDTO.setShopId(shopId);
        queryDTO.setMerchantId(merchantId);
        queryDTO.setStartTime(startTime);
        queryDTO.setIdleStatus(idleStatus == null ? null : idleStatus.getCode());
        queryDTO.setTopN(topN);
        if (itemType != null) {
            queryDTO.setItemType(itemType.getCode());
        }
        if (orderSource != null) {
            queryDTO.setOrderSource(orderSource.getCode());
        }
        if (orderStatus != null) {
            queryDTO.setOrderStatus(orderStatus.getCode());
        }
        if(itemTypes != null){
            queryDTO.setItemTypes(ReportUtil.extractKeys(itemTypes,t->t.getCode()));
        }
        if(orderSources != null){
            queryDTO.setOrderSources(ReportUtil.extractKeys(orderSources,t->t.getCode()));
        }
        if(orderStatusList != null){
            queryDTO.setOrderStatusList(ReportUtil.extractKeys(orderStatusList,t->t.getCode()));
        }

        return queryDTO;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public SupportCouponTimeTypeEnum getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(SupportCouponTimeTypeEnum idleStatus) {
        this.idleStatus = idleStatus;
    }

    public ItemTypeEnum getItemType() {
        return itemType;
    }

    public void setItemType(ItemTypeEnum itemType) {
        this.itemType = itemType;
    }

    public List<ItemTypeEnum> getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(List<ItemTypeEnum> itemTypes) {
        this.itemTypes = itemTypes;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderStatusEnum> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(List<OrderStatusEnum> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public OrderSourceEnum getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(OrderSourceEnum orderSource) {
        this.orderSource = orderSource;
    }

    public List<OrderSourceEnum> getOrderSources() {
        return orderSources;
    }

    public void setOrderSources(List<OrderSourceEnum> orderSources) {
        this.orderSources = orderSources;
    }

    public Integer getTopN() {
        return topN;
    }

    public void setTopN(Integer topN) {
        this.topN = topN;
    }
}
