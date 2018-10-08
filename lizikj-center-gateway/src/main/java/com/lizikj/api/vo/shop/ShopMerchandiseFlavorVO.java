package com.lizikj.api.vo.shop;

import com.lizikj.shop.api.enums.ShopBaseDataCustomStatusEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月18日 下午4:59:48 
 */
@ApiModel
public class ShopMerchandiseFlavorVO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	/**
     * 商品口味偏好ID
     */
    @ApiModelProperty(value = "商品口味偏好ID", required = true)
    private Long merchandiseFlavorId;

    /**
     * 商品口味偏好模板ID
     */
	@ApiModelProperty(value = "商品口味偏好模板ID", required = true)
	private Long merchandiseFlavorTemplateId;

    /**
     * 偏好模板编码：自定义的没有模板编码：1001.不要辣，1002.辣一点，1003.不放香菜，1004.不放蒜，1005.不放葱
     */
	@ApiModelProperty(value = "偏好模板编码：自定义的没有模板编码：1001.不要辣，1002.辣一点，1003.不放香菜，1004.不放蒜，1005.不放葱", required = true)
	private Integer flavorCode;

    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 口味偏好
     */
    @ApiModelProperty(value = "口味偏好", required = true)
    private String theFlavor;

    /**
     * 是否自定义：1.系统，0.自定义
     */
	@ApiModelProperty(value = "是否自定义：见ShopBaseDataCustomStatusEnum：SYSTEM.系统，CUSTOM.自定义。", required = true)
	private ShopBaseDataCustomStatusEnum customStatus;

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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getMerchandiseFlavorTemplateId() {
		return merchandiseFlavorTemplateId;
	}

	public void setMerchandiseFlavorTemplateId(Long merchandiseFlavorTemplateId) {
		this.merchandiseFlavorTemplateId = merchandiseFlavorTemplateId;
	}

	public Integer getFlavorCode() {
		return flavorCode;
	}

	public void setFlavorCode(Integer flavorCode) {
		this.flavorCode = flavorCode;
	}

	public ShopBaseDataCustomStatusEnum getCustomStatus() {
		return customStatus;
	}

	public void setCustomStatus(ShopBaseDataCustomStatusEnum customStatus) {
		this.customStatus = customStatus;
	}
}
