package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author lxl
 * @Date 2018/8/24 14:17
 */
@ApiModel
public class PrizeUseCheckVO {
    @ApiModelProperty(value = "dingdan")
    private String orderNo;
    @ApiModelProperty(value = "卡券列表")
    private List<String> packetCodeList;
    @ApiModelProperty(value = "卡券列表")
    private List<String> couponCodeList;
    @ApiModelProperty(value = "是否通过")
    private Boolean pass;
    @ApiModelProperty(value = "原因")
    private String reason;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<String> getPacketCodeList() {
        return packetCodeList;
    }

    public void setPacketCodeList(List<String> packetCodeList) {
        this.packetCodeList = packetCodeList;
    }

    public List<String> getCouponCodeList() {
        return couponCodeList;
    }

    public void setCouponCodeList(List<String> couponCodeList) {
        this.couponCodeList = couponCodeList;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
