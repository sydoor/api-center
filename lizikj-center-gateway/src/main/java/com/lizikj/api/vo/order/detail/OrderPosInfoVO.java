package com.lizikj.api.vo.order.detail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * POS信息
 * Created by zhoufe on 2017-8-9 11:42:40
 */
@ApiModel
public class OrderPosInfoVO {


    @ApiModelProperty(value = "snNum号")
    private String snNum;//snNum号

    public String getSnNum() {
        return snNum;
    }

    public void setSnNum(String snNum) {
        this.snNum = snNum;
    }
}
