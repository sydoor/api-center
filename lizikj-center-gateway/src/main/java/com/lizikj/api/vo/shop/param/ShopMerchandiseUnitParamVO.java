package com.lizikj.api.vo.shop.param;

import com.lizikj.merchandise.enums.CalcMethodEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月18日 下午4:42:29 
 */
@ApiModel
public class ShopMerchandiseUnitParamVO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	/**
     * 商品单位ID
     */
    @ApiModelProperty(value = "商品单位ID", required = true)
    private Long merchandiseUnitId;

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 计价方式：1.数量，2.重量
     */
	@ApiModelProperty(value = "计价方式：见CalcMethodEnum：NUMBER.数量，WEIGHT.重量。", required = true)
    private CalcMethodEnum priceMothed;

    /**
     * 计价单位
     */
    @ApiModelProperty(value = "计价单位", required = true)
    private String priceUnit;

	/**
	 * @return the merchandiseUnitId
	 */
	public Long getMerchandiseUnitId() {
		return merchandiseUnitId;
	}

	/**
	 * @param merchandiseUnitId the merchandiseUnitId to set
	 */
	public void setMerchandiseUnitId(Long merchandiseUnitId) {
		this.merchandiseUnitId = merchandiseUnitId;
	}

	/**
	 * @return the shopId
	 */
	public Long getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public CalcMethodEnum getPriceMothed() {
		return priceMothed;
	}

	public void setPriceMothed(CalcMethodEnum priceMothed) {
		this.priceMothed = priceMothed;
	}

	/**
	 * @return the priceUnit
	 */
	public String getPriceUnit() {
		return priceUnit;
	}

	/**
	 * @param priceUnit the priceUnit to set
	 */
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
    
    
}
