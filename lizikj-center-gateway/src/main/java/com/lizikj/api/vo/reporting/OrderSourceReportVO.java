package com.lizikj.api.vo.reporting;

import com.lizikj.reporting.enums.OrderSourceReportingEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @author lxl
 * @Date 2018/1/5 18:40
 */
@ApiModel
public class OrderSourceReportVO {
    /**
     * 统计日期字符串
     */
    @ApiModelProperty(value = "统计日期字符串")
    private String reportDateStr;
    /**
     * 订单来源
     */
    @ApiModelProperty(value = "订单来源")
    private OrderSourceReportingEnum orderSourceReportingEnum;
    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额")
    private Long receivedAmount;
    /**
     * 有效订单数
     */
    @ApiModelProperty(value = "有效订单数")
    private Integer validNum;

    public String getReportDateStr() {
        return reportDateStr;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
    }

    public OrderSourceReportingEnum getOrderSourceReportingEnum() {
        return orderSourceReportingEnum;
    }

    public void setOrderSourceReportingEnum(OrderSourceReportingEnum orderSourceReportingEnum) {
        this.orderSourceReportingEnum = orderSourceReportingEnum;
    }

    public Long getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Long receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Integer getValidNum() {
        return validNum;
    }

    public void setValidNum(Integer validNum) {
        this.validNum = validNum;
    }
}
