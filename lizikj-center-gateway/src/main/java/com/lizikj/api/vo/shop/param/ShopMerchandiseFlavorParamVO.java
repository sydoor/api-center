package com.lizikj.api.vo.shop.param;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月18日 下午5:05:50 
 */
@ApiModel
public class ShopMerchandiseFlavorParamVO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
     * 商品口味偏好ID
     */
    @ApiModelProperty(value = "商品口味偏好ID", required = true)
    private Long merchandiseFlavorId;

    /**
     * 口味偏好
     */
    @ApiModelProperty(value = "口味偏好", required = true)
    private String theFlavor;

	/**
	 * @return the merchandiseFlavorId
	 */
	public Long getMerchandiseFlavorId() {
		return merchandiseFlavorId;
	}

	/**
	 * @param merchandiseFlavorId the merchandiseFlavorId to set
	 */
	public void setMerchandiseFlavorId(Long merchandiseFlavorId) {
		this.merchandiseFlavorId = merchandiseFlavorId;
	}

	/**
	 * @return the theFlavor
	 */
	public String getTheFlavor() {
		return theFlavor;
	}

	/**
	 * @param theFlavor the theFlavor to set
	 */
	public void setTheFlavor(String theFlavor) {
		this.theFlavor = theFlavor;
	}
    
    
}
