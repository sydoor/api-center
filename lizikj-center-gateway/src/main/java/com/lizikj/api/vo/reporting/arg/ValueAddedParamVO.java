package com.lizikj.api.vo.reporting.arg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by liangxiaolin on 2017/9/28.
 */
@ApiModel
public class ValueAddedParamVO {
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    /**
     * 服务类型 1：一年增值类型 2：二年增值类型 3：三年增值类型
     */
    @ApiModelProperty(value = "服务类型 1：一年增值类型 2：二年增值类型 3：三年增值类型")
    private Integer vasType;

    /**
     * 续费状态 1 开通 2 续费
     */
    @ApiModelProperty(value = "续费状态 1 开通 2 续费")
    private Integer renewStatus;

    /**
     * 开通开始时间
     */
    @ApiModelProperty(value = "开通开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    /**
     * 开通结束时间
     */
    @ApiModelProperty(value = "开通结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getVasType() {
        return vasType;
    }

    public void setVasType(Integer vasType) {
        this.vasType = vasType;
    }

    public Integer getRenewStatus() {
        return renewStatus;
    }

    public void setRenewStatus(Integer renewStatus) {
        this.renewStatus = renewStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


}
