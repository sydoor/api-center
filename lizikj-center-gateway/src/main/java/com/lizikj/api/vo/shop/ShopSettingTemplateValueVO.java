package com.lizikj.api.vo.shop;

import com.lizikj.shop.api.enums.DefaultStatusEnum;
import com.lizikj.shop.api.enums.FeeStatusEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model for table -- shop_setting_template_value
 * Created by zhoufe on 2017-07-13 18:03:50
 */
@ApiModel
public class ShopSettingTemplateValueVO extends BaseDTO {
	
    private static final long serialVersionUID = 1L;

	/**
     * 字典值ID
     */
	@ApiModelProperty(value = "字典值ID", required = true)
    private Long settingTemplateValueId;

    /**
     * 店铺配置模版ID
     */
	@ApiModelProperty(value = "店铺配置模版ID", required = true)
    private Long settingTemplateId;

    /**
     * 模板编码
     */
	@ApiModelProperty(value = "模板编码", required = true)
    private Integer settingCode;

    /**
     * 模板值名
     */
	@ApiModelProperty(value = "模板值名", required = true)
    private String valueName;

    /**
     * 模板值编码
     */
	@ApiModelProperty(value = "模板值编码", required = true)
    private Integer valueCode;

    /**
     * 模板值
     */
	@ApiModelProperty(value = "模板值", required = true)
    private String value;

    /**
     * 是否费用：0.否，1.是
     */
	@ApiModelProperty(value = "是否费用：见FeeStatusEnum：NO_FEE.否，IS_FEE.是", required = true)
    private FeeStatusEnum feeStatus;

    /**
     * 排序
     */
	@ApiModelProperty(value = "排序", required = true)
    private Integer orderNo;

    /**
     * 是否默认：1，是默认，0.否默认
     */
	@ApiModelProperty(value = "是否默认：见DefaultStatusEnum：DEFAULT.是默认，UN_DEFAULT.否默认", required = true)
    private DefaultStatusEnum defaultStatus;
    
    /**
     * 字典值ID
     */
    public Long getSettingTemplateValueId() {
        return settingTemplateValueId;
    }

    /**
     * 字典值ID
     * @param settingTemplateValueId the value for shop_setting_template_value.setting_template_value_id
     */
    public void setSettingTemplateValueId(Long settingTemplateValueId) {
        this.settingTemplateValueId = settingTemplateValueId;
    }

    /**
     * 店铺配置模版ID
     */
    public Long getSettingTemplateId() {
        return settingTemplateId;
    }

    /**
     * 店铺配置模版ID
     * @param settingTemplateId the value for shop_setting_template_value.setting_template_id
     */
    public void setSettingTemplateId(Long settingTemplateId) {
        this.settingTemplateId = settingTemplateId;
    }

    /**
     * 模板编码
     */
    public Integer getSettingCode() {
        return settingCode;
    }

    /**
     * 模板编码
     * @param settingCode the value for shop_setting_template_value.setting_code
     */
    public void setSettingCode(Integer settingCode) {
        this.settingCode = settingCode;
    }

    /**
     * 模板值名
     */
	public String getValueName() {
		return valueName;
	}

    /**
     * 模板值名
     * @param templateValueName the value for shop_setting_template_value.template_value_name
     */
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

    /**
     * 模板值编码
     */
	public Integer getValueCode() {
		return valueCode;
	}

    /**
     * 模板值编码
     * @param templateValueCode the value for shop_setting_template_value.template_value_code
     */
	public void setValueCode(Integer valueCode) {
		this.valueCode = valueCode;
	}

    /**
     * 模板值
     */
	public String getValue() {
		return value;
	}

    /**
     * 模板值
     * @param templateValue the value for shop_setting_template_value.template_value
     */
	public void setValue(String value) {
		this.value = value;
	}

	/**
     * 排序
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * 排序
     * @param orderNo the value for shop_setting_template_value.order_no
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
}