package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author lxl
 * @Date 2018/8/7 16:58
 */
@ApiModel(value = "收银员支付方式统计")
public class CashierPaymentReportVO {
    @ApiModelProperty(value = "收银员ID")
    private Long cashierId;
    @ApiModelProperty(value = "收银员名称")
    private String cashierName;
    List<PaymentReportVO> paymentReportVOList;

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public List<PaymentReportVO> getPaymentReportVOList() {
        return paymentReportVOList;
    }

    public void setPaymentReportVOList(List<PaymentReportVO> paymentReportVOList) {
        this.paymentReportVOList = paymentReportVOList;
    }
}
