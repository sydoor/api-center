package com.lizikj.api.vo.shop;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/** 
 * @author zhoufe 
 * @date 2017年7月19日 上午10:59:37 
 */
@ApiModel
public class ShopAreaSummaryVO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 区域
	 */
	@ApiModelProperty(value = "区域", required = true)
	private ShopAreaVO shopArea;
	
	/**
	 * 区域下的桌台状态
	 */
	@ApiModelProperty(value = "区域下的桌台状态", required = true)
	private List<ShopAreaDeskVO> shopAreaDesks;
	
	/**
	 * 区域下的会员
	 */
	@ApiModelProperty(value = "区域下的会员", required = true)
	private List<ShopAreaMemberVO> shopAreaMembers;

	public ShopAreaVO getShopArea() {
		return shopArea;
	}

	public void setShopArea(ShopAreaVO shopArea) {
		this.shopArea = shopArea;
	}

	public List<ShopAreaDeskVO> getShopAreaDesks() {
		return shopAreaDesks;
	}

	public void setShopAreaDesks(List<ShopAreaDeskVO> shopAreaDesks) {
		this.shopAreaDesks = shopAreaDesks;
	}

	public List<ShopAreaMemberVO> getShopAreaMembers() {
		return shopAreaMembers;
	}

	public void setShopAreaMembers(List<ShopAreaMemberVO> shopAreaMembers) {
		this.shopAreaMembers = shopAreaMembers;
	}
}
