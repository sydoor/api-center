package com.lizikj.api.vo.order;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 订单统一下单结果
 *
 * @author zone
 * @date 2017/9/18
 */
public class OrderPayResultVO {
	@ApiModelProperty(value = "结果", required = true)
	private Boolean result;

	@ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

	@ApiModelProperty(value = "订单编号", required = true)
    private String orderNo;

	@ApiModelProperty(value = "业务数据", required = true)
    private String bizData;

    /**
     * 支付交易内部流水号
     */
    @ApiModelProperty(value = "支付交易内部流水号", required = false)
    private String innerTradeNo;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

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

    public String getBizData() {
        return bizData;
    }

    public void setBizData(String bizData) {
        this.bizData = bizData;
    }

    public String getInnerTradeNo() {
        return innerTradeNo;
    }

    public void setInnerTradeNo(String innerTradeNo) {
        this.innerTradeNo = innerTradeNo;
    }
}
