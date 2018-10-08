package com.lizikj.api.vo.shop;

import com.lizikj.common.enums.RemoveStatusEnum;
import com.lizikj.shop.api.enums.ShopAreaDefaultStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/** 
 * @author zhoufe 
 * @date 2017年7月19日 上午9:53:07 
 */
@ApiModel
public class ShopAreaVO {
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
	 * 区域ID
	 */
	@ApiModelProperty(value = "店铺ID", required = true)
	private Long shopId;

    /**
     * 是否默认：DEFAULT.默认区域，CUSTOM.自定义区域
     */
	@ApiModelProperty(value = "是否默认：见ShopAreaDefaultStatusEnum：DEFAULT.默认区域，CUSTOM.自定义区域。", required = true)
    private ShopAreaDefaultStatusEnum defaultStatus;

    /**
     * 茶位费：开启茶位费，输入默认茶位费时生成默认区域；增加区域时，要为每个区域输入茶位费
     */
	@ApiModelProperty(value = "茶位费：开启茶位费，输入默认茶位费时生成默认区域；增加区域时，要为每个区域输入茶位费", required = true)
    private Integer teaSeatFee;

	/**
	 * 该区域下的桌台总数
	 */
	@ApiModelProperty(value = "该区域下的桌台总数", required = true)
	private Integer deskTotal;

	/**
	 * 该区域下的桌台
	 */
	@ApiModelProperty(value = "该区域下的桌台：在listAreaAndDesk接口查询是才封装数据", required = true)
	private List<ShopDeskVO> shopDesks;


	/**
	 * 是否删除:1.删除；0.未删除
	 */
	@ApiModelProperty(value = "是否删除：见：RemoveStatusEnum。", required = true)
	private RemoveStatusEnum removeStatus;

	@ApiModelProperty(value = "更新时间", required = true)
	private Date updateTime;

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

	public ShopAreaDefaultStatusEnum getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(ShopAreaDefaultStatusEnum defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

	/**
	 * @return the teaSeatFee
	 */
	public Integer getTeaSeatFee() {
		return teaSeatFee;
	}

	/**
	 * @param teaSeatFee the teaSeatFee to set
	 */
	public void setTeaSeatFee(Integer teaSeatFee) {
		this.teaSeatFee = teaSeatFee;
	}

	public Integer getDeskTotal() {
		return deskTotal;
	}

	public void setDeskTotal(Integer deskTotal) {
		this.deskTotal = deskTotal;
	}

	public List<ShopDeskVO> getShopDesks() {
		return shopDesks;
	}

	public void setShopDesks(List<ShopDeskVO> shopDesks) {
		this.shopDesks = shopDesks;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public RemoveStatusEnum getRemoveStatus() {
		return removeStatus;
	}

	public void setRemoveStatus(RemoveStatusEnum removeStatus) {
		this.removeStatus = removeStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
