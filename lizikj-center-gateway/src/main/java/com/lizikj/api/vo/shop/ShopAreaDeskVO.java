package com.lizikj.api.vo.shop;

import com.lizikj.shop.api.enums.DeskUsedStatusEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 该状态下的桌台汇总
 * @author zhoufe 
 * @date 2017年7月19日 上午11:19:56 
 */
@ApiModel
public class ShopAreaDeskVO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 桌台状态
	 */

	@ApiModelProperty(value = "使用状态：见：DeskUsedStatusEnum：FREE. 空闲，WAIT_REC. 待接单，WAIT_ORDER. 待点单，" +
			"WAIT_PAYMENT. 待结账，WAIT_DESK_CLEAN. 待清台，LOCKED. 锁定。", required = true)
	private DeskUsedStatusEnum usedStatus;
	
	/**
	 * 桌台状态名称
	 */
	@ApiModelProperty(value = "桌台状态名称：见：DeskUsedStatusEnum：", required = true)
	private String usedStatusName;

	/**
	 * 桌台总数
	 */
	@ApiModelProperty(value = "该状态下的桌台总数", required = true)
	private Integer deskTotal;


	public DeskUsedStatusEnum getUsedStatus() {
		return usedStatus;
	}

	public void setUsedStatus(DeskUsedStatusEnum usedStatus) {
		this.usedStatus = usedStatus;
	}

	/**
	 * @return the usedStatusName
	 */
	public String getUsedStatusName() {
		return usedStatusName;
	}


	/**
	 * @param usedStatusName the usedStatusName to set
	 */
	public void setUsedStatusName(String usedStatusName) {
		this.usedStatusName = usedStatusName;
	}


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
	
	
	
	
}
