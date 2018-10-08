package com.lizikj.api.vo.shop;

import java.util.Date;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月17日 下午9:10:16 
 */
@ApiModel
public class ShopMerchandiseTaskVO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;

	/**
     * 商品任务ID
     */
	@ApiModelProperty(value = "商品任务ID", required = true)
    private Long shopMerchandiseTasktId;

    /**
     * 店铺ID
     */
	@ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 商品ID
     */
	@ApiModelProperty(value = "商品ID", required = true)
    private String goodsId;

    /**
     * 任务执行时间：计划执行任务的时间，到时见就执行
     */
	@ApiModelProperty(value = "任务执行时间：计划执行任务的时间，到时见就执行", required = true)
    private Date taskDate;

	/**
	 * @return the shopMerchandiseTasktId
	 */
	public Long getShopMerchandiseTasktId() {
		return shopMerchandiseTasktId;
	}

	/**
	 * @param shopMerchandiseTasktId the shopMerchandiseTasktId to set
	 */
	public void setShopMerchandiseTasktId(Long shopMerchandiseTasktId) {
		this.shopMerchandiseTasktId = shopMerchandiseTasktId;
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
	 * @return the goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return the taskDate
	 */
	public Date getTaskDate() {
		return taskDate;
	}

	/**
	 * @param taskDate the taskDate to set
	 */
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
    
    
}
