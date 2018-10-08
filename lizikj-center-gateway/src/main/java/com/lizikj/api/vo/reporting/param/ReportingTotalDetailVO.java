package com.lizikj.api.vo.reporting.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA. adept
 * User:  liuyuntao
 * Date: 2017/7/18
 * Time:  21:02
 */
@ApiModel(value = "ReportingTotalDetailVO", description = "报表统计明细")
public class ReportingTotalDetailVO implements Serializable{
    @ApiModelProperty(name="totalAmount", value="支付方式")
    private Double totalAmount;//总金额
    @ApiModelProperty(name="nums", value="总笔数")
    private Long nums;//总笔数
    @ApiModelProperty(name="tradeSource", value="交易来源")
    private Byte tradeSource;//交易来源 TradeSourceEnum.java中的值
    @ApiModelProperty(name="payType", value="支付方式")
    private Byte payType; //支付方式 PayTypeEnum.java中的值

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getNums() {
        return nums;
    }

    public void setNums(Long nums) {
        this.nums = nums;
    }

    public Byte getTradeSource() {
        return tradeSource;
    }

    public void setTradeSource(Byte tradeSource) {
        this.tradeSource = tradeSource;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }
}
