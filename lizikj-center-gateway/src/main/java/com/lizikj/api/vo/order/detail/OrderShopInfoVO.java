package com.lizikj.api.vo.order.detail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单店铺商户信息
 * Created by zhoufe on 2017-8-9 11:42:40
 */
@ApiModel
public class OrderShopInfoVO {

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "店铺名称")
    private Long merchantId;

    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 店铺LOGO
     */
    @ApiModelProperty(value = "店铺LOGO")
    private Long logoPicId;


    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getLogoPicId() {
        return logoPicId;
    }

    public void setLogoPicId(Long logoPicId) {
        this.logoPicId = logoPicId;
    }
}
