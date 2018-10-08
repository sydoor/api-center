package com.lizikj.api.vo.shop.param;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月19日 上午10:43:15 
 */
@ApiModel
public class ShopDeskParamVO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	/**
     * 桌子ID
     */
	@ApiModelProperty(value = "桌子ID", required = true)
    private Long areaDeskId;

    /**
     * 店铺区域ID
     */
	@ApiModelProperty(value = "店铺区域ID", required = true)
    private Long shopAreaId;

    /**
     * 餐桌名称
     */
	@ApiModelProperty(value = "餐桌名称", required = true)
    private String deskName;

    /**
     * 座位数
     */
	@ApiModelProperty(value = "座位数", required = true)
    private Integer seatNum;

    /**
     * 二维码图片地址：放在资源系统中（目前七牛），缩略图用20x20的方式拼装media_id得到的地址获得
     */
	@ApiModelProperty(value = "二维码图片地址：放在资源系统中（目前七牛），缩略图用20x20的方式拼装media_id得到的地址获得", required = true)
    private Long mediaId;

	@ApiModelProperty(value = "父ID：没有默认为0", required = true)
	private Long parentId;

	@ApiModelProperty(value = "是否拼桌", required = true)
	private Boolean mergeFlag;

	@ApiModelProperty(value = "层级:0.父，1.子，2.1的子", required = true)
	private Integer level;

	/**
	 * @return the areaDeskId
	 */
	public Long getAreaDeskId() {
		return areaDeskId;
	}

	/**
	 * @param areaDeskId the areaDeskId to set
	 */
	public void setAreaDeskId(Long areaDeskId) {
		this.areaDeskId = areaDeskId;
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
	 * @return the deskName
	 */
	public String getDeskName() {
		return deskName;
	}

	/**
	 * @param deskName the deskName to set
	 */
	public void setDeskName(String deskName) {
		this.deskName = deskName;
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

	/**
	 * @return the mediaId
	 */
	public Long getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean getMergeFlag() {
		return mergeFlag;
	}

	public void setMergeFlag(Boolean mergeFlag) {
		this.mergeFlag = mergeFlag;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
