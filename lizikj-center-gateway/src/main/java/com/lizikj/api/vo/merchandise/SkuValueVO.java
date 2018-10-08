package com.lizikj.api.vo.merchandise;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michael.Huang
 * @date 2017/6/23
 */
@ApiModel
public class SkuValueVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "ID", required = true)
    private Long id;

    /**
     * 值
     */
    @ApiModelProperty(value = "普通规格属性值，如果是套餐此value为null,此时skuPackageValue不为null", required = true)
    private String value;

    /**
     * 对应的SKU属性ID
     */
    @ApiModelProperty(value = "所属的属性", required = true)
    private Long skuPropertyId;

    /**
     * 对应的SKU属性名
     */
    @ApiModelProperty(value = "所属的属性名", required = true)
    private String skuPropertyName;

    /**
     * 套餐skuValue
     */
    @ApiModelProperty(value = "套餐规格属性值", required = false)
    private SkuPackageValueVO skuPackageValue;


    /**
     * 是否选中激活(订单物品以及购物车物品中有意义)
     */
    @ApiModelProperty(value = "是否选中激活(订单物品以及购物车物品中有意义)", required = false)
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuPropertyId() {
        return skuPropertyId;
    }

    public void setSkuPropertyId(Long skuPropertyId) {
        this.skuPropertyId = skuPropertyId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SkuPackageValueVO getSkuPackageValue() {
        return skuPackageValue;
    }

    public void setSkuPackageValue(SkuPackageValueVO skuPackageValue) {
        this.skuPackageValue = skuPackageValue;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

	public String getSkuPropertyName() {
		return skuPropertyName;
	}

	public void setSkuPropertyName(String skuPropertyName) {
		this.skuPropertyName = skuPropertyName;
	}
}
