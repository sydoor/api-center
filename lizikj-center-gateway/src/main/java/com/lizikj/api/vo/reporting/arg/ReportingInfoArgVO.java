package com.lizikj.api.vo.reporting.arg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by adept on 2017/7/18.
 */
@ApiModel(value = "ReportingInfoArgVO", description = "报表搜索对象")
public class ReportingInfoArgVO implements Serializable{
    private static final long serialVersionUID = -1213199417204373510L;

    @ApiModelProperty(value = "店铺id",name="shopId",dataType ="Long")
    private Long shopId;//店铺id
    @ApiModelProperty(value = "snNum号",name="snNum",dataType ="String")
    private String snNum;

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
}
