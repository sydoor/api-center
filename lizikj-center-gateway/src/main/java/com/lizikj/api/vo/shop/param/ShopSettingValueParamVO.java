package com.lizikj.api.vo.shop.param;

import com.lizikj.shop.api.enums.DefaultStatusEnum;
import com.lizikj.shop.api.enums.ShopSettingFeeModeEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月19日 下午4:05:58 
 */
@ApiModel
public class ShopSettingValueParamVO extends BaseDTO{

	private static final long serialVersionUID = 1L;
	
	/**
     * 设置值ID
     */
	@ApiModelProperty(value = "设置值ID", required = true)
    private Long settingValueId;
	
	/**
     * 设置值名：如收费方式
     */
	@ApiModelProperty(value = "设置值名", required = true)
    private String valueName;

    /**
     * 设置值：如1.按用餐人数（元/人），2.按桌台（元/桌）3.按用餐人数（元/人）
     */
	@ApiModelProperty(value = "设置值", required = true)
    private String value;

	@ApiModelProperty(value = "收费模式：见ShopSettingFeeModeEnum：DESK_MODE.按桌台（元/桌），PEOPLE_MODE.按用餐人数（元/人），ORDER_MODE.按订单（元/单）", required = true)
	private ShopSettingFeeModeEnum feeMode;

	@ApiModelProperty(value = "是否默认：见DefaultStatusEnum：DEFAULT.已默认,UN_DEFAULT.非默认", required = true)
	private DefaultStatusEnum defaultStatus;

	/**
	 * @return the valueName
	 */
	public String getValueName() {
		return valueName;
	}

	/**
	 * @param valueName the valueName to set
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the settingValueId
	 */
	public Long getSettingValueId() {
		return settingValueId;
	}

	/**
	 * @param settingValueId the settingValueId to set
	 */
	public void setSettingValueId(Long settingValueId) {
		this.settingValueId = settingValueId;
	}

	public ShopSettingFeeModeEnum getFeeMode() {
		return feeMode;
	}

	public void setFeeMode(ShopSettingFeeModeEnum feeMode) {
		this.feeMode = feeMode;
	}

	public DefaultStatusEnum getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(DefaultStatusEnum defaultStatus) {
		this.defaultStatus = defaultStatus;
	}
}
