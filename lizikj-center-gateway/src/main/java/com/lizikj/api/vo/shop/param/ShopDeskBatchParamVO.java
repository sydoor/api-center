package com.lizikj.api.vo.shop.param;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月19日 上午10:35:17 
 */
@ApiModel
public class ShopDeskBatchParamVO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称前缀
	 */
	@ApiModelProperty(value = "名称前缀", required = true)
	private String namePrefix;
	
	/**
	 * 开始序号
	 */
	@ApiModelProperty(value = "开始序号", required = true)
	private Integer startIndex;
	
	/**
	 * 结束序号
	 */
	@ApiModelProperty(value = "结束序号", required = true)
	private Integer endIndex;
	
	/**
	 * 所在区域
	 */
	@ApiModelProperty(value = "所在区域", required = true)
	private Long shopAreaId; 

	/**
	 * 人数（位）
	 */
	@ApiModelProperty(value = "人数（位", required = true)
	private Integer seatNum;

	/**
	 * @return the namePrefix
	 */
	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * @param namePrefix the namePrefix to set
	 */
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	/**
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the endIndex
	 */
	public Integer getEndIndex() {
		return endIndex;
	}

	/**
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

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
	 * @return the seatNum
	 */
	public Integer getSeatNum() {
		return seatNum;
	}

	/**
	 * @param seatNum the seatNum to set
	 */
	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}
	
	
}
