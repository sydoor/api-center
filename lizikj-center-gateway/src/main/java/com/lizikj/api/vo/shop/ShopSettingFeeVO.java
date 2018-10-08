package com.lizikj.api.vo.shop;

import java.io.Serializable;

import com.lizikj.shop.api.enums.OpenStatusEnum;
import com.lizikj.shop.api.enums.ShopSettingFeeModeEnum;
import com.lizikj.shop.api.enums.ShopSettingTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @auth zone
 * @date 2018-05-03
 */
@ApiModel
public class ShopSettingFeeVO implements Serializable {

	@ApiModelProperty(value = "配置ID", required = true)
	private Long shopSettingId;

	@ApiModelProperty(value = "配置编码", required = true)
	private Integer settingCode;

	@ApiModelProperty(value = "收费名称", required = true)
	private String feeName;

	@ApiModelProperty(value = "收费方式", required = true)
	private ShopSettingFeeModeEnum feeMode;

	@ApiModelProperty(value = "费用", required = true)
	private Long fee;

	@ApiModelProperty(value = "费用类型", required = true)
	private ShopSettingTypeEnum shopSettingType;

	@ApiModelProperty(value = "是否开启", required = true)
    private OpenStatusEnum openStatus;

	public Long getShopSettingId() {
		return shopSettingId;
	}

	public void setShopSettingId(Long shopSettingId) {
		this.shopSettingId = shopSettingId;
	}

	public Integer getSettingCode() {
		return settingCode;
	}

	public void setSettingCode(Integer settingCode) {
		this.settingCode = settingCode;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public ShopSettingFeeModeEnum getFeeMode() {
		return feeMode;
	}

	public void setFeeMode(ShopSettingFeeModeEnum feeMode) {
		this.feeMode = feeMode;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public ShopSettingTypeEnum getShopSettingType() {
		return shopSettingType;
	}

	public void setShopSettingType(ShopSettingTypeEnum shopSettingType) {
		this.shopSettingType = shopSettingType;
	}

	public OpenStatusEnum getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(OpenStatusEnum openStatus) {
		this.openStatus = openStatus;
	}

}
