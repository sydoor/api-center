package com.lizikj.api.vo.reporting.arg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by adept on 2017/7/18.
 */
@ApiModel(value = "ReportingInfoPosArgVO", description = "pos报表搜索对象")
public class ReportingInfoPosArgVO implements Serializable{
    private static final long serialVersionUID = -1213199417204373510L;

    @ApiModelProperty(value = "店铺id",name="shopId",dataType ="Long",required = true)
    private Long shopId;//店铺id
    @ApiModelProperty(value = "snNum号",name="snNum",dataType ="String",required = false)
    private String snNum;
    @ApiModelProperty(value = "统计类型(8100日统计DAY; 8200月统计MONTH; 8300年统计YEAR;)",name="type",dataType ="Integer",required = true)
    private Byte type;//统计类型 ReportingTypeEnum.java中的值
    @ApiModelProperty(value = "支付方式(1现金MONEY; 4银行卡BANK_CARD; 6支付宝ALi; 7微信WX; 8李子贝LIZI_SHELL; 11预付券PREPAY_CARD; 99美团券MEITUAN_CARD)",name="payType",dataType ="Integer",required = false)
    private Byte payType;//支付方式 PayTypeEnum.java中的值
    @ApiModelProperty(value = "统计开始日期(格式：yyyy 或 yyyy-MM 或 yyyy-MM-dd)",name="startDate",dataType ="String",required = false)
    private String startDate;//统计开始日期

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSnNum() {
        return snNum;
    }

    public void setSnNum(String snNum) {
        this.snNum = snNum;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
