package com.lizikj.api.vo.shop.param;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月19日 上午10:09:37 
 */
@ApiModel
public class ShopAreaParamVO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	/**
     * 店铺区域ID
     */
	@ApiModelProperty(value = "店铺区域ID", required = true)
    private Long shopAreaId;

    /**
     * 区域名称
     */
	@ApiModelProperty(value = "区域名称", required = true)
    private String areaName;

	/**
	 * 茶位费
	 */
	@ApiModelProperty(value = "茶位费", required = true)
	private long teaSeatFee;



	/**
	 * @return the shopAreaId
	 */
	public Long getShopAreaId() {
		return shopAreaId;
	}

	/**
	 * @param shopAreaId the shopAreaId to set
	 */
	public void setShopAreaId(Long shopAreaId) {
		this.shopAreaId = shopAreaId;
	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public long getTeaSeatFee() {
		return teaSeatFee;
	}

	public void setTeaSeatFee(long teaSeatFee) {
		this.teaSeatFee = teaSeatFee;
	}
}
