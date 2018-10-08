package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by adept on 2017/7/18.
 */
@ApiModel(value = "ReportingInfoVo", description = "报表对象")
public class ReportingInfoVo implements Serializable {
    private static final long serialVersionUID = -2732951030019232122L;

    @ApiModelProperty(name="merchantId", value="商户id")
    private Long merchantId;//商户id

    @ApiModelProperty(name="shopId", value="店铺id")
    private Long shopId;//店铺id

    @ApiModelProperty(name="type", value="统计类型")
    private Byte type;//统计类型

    @ApiModelProperty(name="payType", value="支付方式")
    private Byte payType;//支付方式 PayTypeEnum.java中的值

    @ApiModelProperty(name="startDate", value="统计开始日期")
    private Date startDate;//统计开始日期

    @ApiModelProperty(name="payAmount", value="支付金额")
    private Long payAmount;//支付金额

    @ApiModelProperty(name="refundAmount", value="退款金额")
    private Long refundAmount;//退款金额

    @ApiModelProperty(name="status", value="统计状态")
    private Byte status;//统计状态

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
