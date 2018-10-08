package com.lizikj.api.vo.order.param;

import java.util.List;

import com.lizikj.order.enums.OrderSourceEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michael.Huang
 * @date 2017/10/11 14:05
 */
public class QuickPayParamVO {

    @ApiModelProperty(value = "融合支付信息内容")
    private List<PayParamVO> payParamList;

    @ApiModelProperty(value = "前台传入订单应付总金额 界面上看到的金额和服务端实际计算的金额有出入时，提醒界面，重新刷新计价 再完成支付")
    private Long totalAmount;

    @ApiModelProperty(value = "来源：见OrderSourceEnum：H5.H5下单，POS.pos主动下单，QUICK.快捷收银台下单，QR_CODE.店铺收款码下单，VALUE_ADD.增值服务订单，LZ_PURCHASE.李子会员购买，SC.小程序，LMW.撩美味，OTHER.其他。")
    private OrderSourceEnum orderSource;

    @ApiModelProperty(value = "订单号，快捷收银时，有前端生成：SD+xxx")
    private String orderNo;

    @ApiModelProperty(value = "现金支付是接收到的金额")
    private Long receiveCashAmount;

    @ApiModelProperty(value = "现金支付是接收到的金额的找零")
    private Long cashChangeAmount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<PayParamVO> getPayParamList() {
        return payParamList;
    }

    public void setPayParamList(List<PayParamVO> payParamList) {
        this.payParamList = payParamList;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderSourceEnum getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(OrderSourceEnum orderSource) {
        this.orderSource = orderSource;
    }

    public Long getReceiveCashAmount() {
        return receiveCashAmount;
    }

    public void setReceiveCashAmount(Long receiveCashAmount) {
        this.receiveCashAmount = receiveCashAmount;
    }

    public Long getCashChangeAmount() {
        return cashChangeAmount;
    }

    public void setCashChangeAmount(Long cashChangeAmount) {
        this.cashChangeAmount = cashChangeAmount;
    }
}
