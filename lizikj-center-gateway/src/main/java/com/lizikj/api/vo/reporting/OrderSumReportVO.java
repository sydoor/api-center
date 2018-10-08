package com.lizikj.api.vo.reporting;

import com.lizikj.order.enums.OrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/1/16 16:51
 */
@ApiModel
public class OrderSumReportVO {
    @ApiModelProperty(value = "商户")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    @ApiModelProperty(value = "统计日期")
    private String reportTime;
    @ApiModelProperty(value = "订单状态")
    private OrderStatusEnum orderStatusEnum;
    @ApiModelProperty(value = "消费金额 ")
    private Long amount;
    @ApiModelProperty(value = "消费笔数")
    private Integer num;
    @ApiModelProperty(value = "就餐人数")
    private Integer numOfGuests;
    @ApiModelProperty(value = "人均消费")
    private Long avgOfGuests;

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

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public OrderStatusEnum getOrderStatusEnum() {
        return orderStatusEnum;
    }

    public void setOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
        this.orderStatusEnum = orderStatusEnum;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNumOfGuests() {
        return numOfGuests;
    }

    public void setNumOfGuests(Integer numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    public Long getAvgOfGuests() {
        return avgOfGuests;
    }

    public void setAvgOfGuests(Long avgOfGuests) {
        this.avgOfGuests = avgOfGuests;
    }
}
