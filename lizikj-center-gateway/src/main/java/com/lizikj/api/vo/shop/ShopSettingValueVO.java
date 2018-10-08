package com.lizikj.api.vo.shop;

import com.lizikj.shop.api.enums.DefaultStatusEnum;
import com.lizikj.shop.api.enums.FeeStatusEnum;
import com.lizikj.shop.api.enums.ShopSettingFeeModeEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Model for table -- shop_setting_value
 * Created by zhoufe on 2017-07-13 18:03:50
 */
@ApiModel
public class ShopSettingValueVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 设置值ID
     */
    @ApiModelProperty(value = "设置值ID", required = true)
    private Long settingValueId;

    /**
     * 店铺使用的配置ID
     */
    @ApiModelProperty(value = "店铺使用的配置ID", required = true)
    private Long shopSettingId;

    /**
     * 设置编码
     */
    @ApiModelProperty(value = "设置编码", required = true)
    private Integer settingCode;

    /**
     * 设置值名
     */
    @ApiModelProperty(value = "设置值名", required = true)
    private String valueName;

    /**
     * 设置值编码
     */
    @ApiModelProperty(value = "设置值编码", required = true)
    private Integer valueCode;

    /**
     * 设置值
     */
    @ApiModelProperty(value = "设置值", required = true)
    private String value;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    private Integer orderNo;

    /**
     * 是否费用：0.否，1.是
     */
    @ApiModelProperty(value = "是否费用：NO_FEE.非费用，IS_FEE.是费用", required = true)
    private FeeStatusEnum feeStatus;

    /**
     * 是否默认：1，是默认，0.否默认
     */
    @ApiModelProperty(value = "是否默认：见DefaultStatusEnum：DEFAULT.已默认,UN_DEFAULT.非默认", required = true)
    private DefaultStatusEnum defaultStatus;

    /**
     * 收费方式
     */
    @ApiModelProperty(value = "收费方式：见ShopSettingFeeModeEnum：DESK_MODE.按桌台（元/桌）, PEOPLE_MODE.按用餐人数, ORDER_MODE.按订单（元/单）", required = true)
    private ShopSettingFeeModeEnum feeMode;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;


    /**
     * 字典值ID
     */
    public Long getSettingValueId() {
        return settingValueId;
    }

    /**
     * 字典值ID
     *
     * @param settingValueId the value for shop_setting_value.setting_value_id
     */
    public void setSettingValueId(Long settingValueId) {
        this.settingValueId = settingValueId;
    }

    /**
     * 店铺使用的配置ID
     */
    public Long getShopSettingId() {
        return shopSettingId;
    }

    /**
     * 店铺使用的配置ID
     *
     * @param shopSettingId the value for shop_setting_value.shop_setting_id
     */
    public void setShopSettingId(Long shopSettingId) {
        this.shopSettingId = shopSettingId;
    }

    /**
     * 设置编码
     */
    public Integer getSettingCode() {
        return settingCode;
    }

    /**
     * 设置编码
     *
     * @param settingCode the value for shop_setting_value.setting_code
     */
    public void setSettingCode(Integer settingCode) {
        this.settingCode = settingCode;
    }

    /**
     * 设置值名
     */
    public String getValueName() {
        return valueName;
    }

    /**
     * 设置值名
     *
     * @param valueName the value for shop_setting_value.value_name
     */
    public void setValueName(String valueName) {
        this.valueName = valueName == null ? null : valueName.trim();
    }

    /**
     * 设置值编码
     */
    public Integer getValueCode() {
        return valueCode;
    }

    /**
     * 设置值编码
     *
     * @param valueCode the value for shop_setting_value.value_code
     */
    public void setValueCode(Integer valueCode) {
        this.valueCode = valueCode;
    }

    /**
     * 设置值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置值
     *
     * @param value the value for shop_setting_value.value
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     *
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo the value for shop_setting_value.order_no
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public FeeStatusEnum getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(FeeStatusEnum feeStatus) {
        this.feeStatus = feeStatus;
    }

    public DefaultStatusEnum getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(DefaultStatusEnum defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public ShopSettingFeeModeEnum getFeeMode() {
        return feeMode;
    }

    public void setFeeMode(ShopSettingFeeModeEnum feeMode) {
        this.feeMode = feeMode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}