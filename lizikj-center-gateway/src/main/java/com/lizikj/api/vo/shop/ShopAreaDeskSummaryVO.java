package com.lizikj.api.vo.shop;

import java.io.Serializable;
import java.util.List;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月19日 上午10:59:37 
 */
@ApiModel
public class ShopAreaDeskSummaryVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 桌台总数
	 */
	@ApiModelProperty(value = "桌台总数", required = true)
	private Integer deskTotal;
	
	/**
	 * 桌台区域明细：如果是查询区域统计，则有值
	 */
	@ApiModelProperty(value = "桌台区域明细：如果是查询区域统计，则有值", required = true)
	private List<ShopAreaVO> shopAreas;
	
	/**
	 * 桌台状态明细：如果是按状态域统计，则有值
	 */
	@ApiModelProperty(value = "桌台状态明细：如果是按状态域统计，则有值", required = true)
	private List<ShopAreaDeskVO> shopAreaDesks;
	
	/**
	 * 桌台人数明细：如果是按人数统计，则有值
	 */
	@ApiModelProperty(value = "桌台人数明细：如果是按人数统计，则有值", required = true)
	private List<ShopSeatNumDeskVO> shopSeatNumDesks;

	/**
	 * @return the deskTotal
	 */
	public Integer getDeskTotal() {
		return deskTotal;
	}

	/**
	 * @param deskTotal the deskTotal to set
	 */
	public void setDeskTotal(Integer deskTotal) {
		this.deskTotal = deskTotal;
	}

	public List<ShopAreaVO> getShopAreas() {
		return shopAreas;
	}

	public void setShopAreas(List<ShopAreaVO> shopAreas) {
		this.shopAreas = shopAreas;
	}

	public List<ShopAreaDeskVO> getShopAreaDesks() {
		return shopAreaDesks;
	}

	public void setShopAreaDesks(List<ShopAreaDeskVO> shopAreaDesks) {
		this.shopAreaDesks = shopAreaDesks;
	}

	public List<ShopSeatNumDeskVO> getShopSeatNumDesks() {
		return shopSeatNumDesks;
	}

	public void setShopSeatNumDesks(List<ShopSeatNumDeskVO> shopSeatNumDesks) {
		this.shopSeatNumDesks = shopSeatNumDesks;
	}
}
