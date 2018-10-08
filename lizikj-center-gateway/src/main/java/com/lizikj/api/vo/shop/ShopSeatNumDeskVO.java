package com.lizikj.api.vo.shop;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月19日 上午11:21:15 
 */
@ApiModel
public class ShopSeatNumDeskVO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
    /**
     * 座位数：区间开始
     */
	@ApiModelProperty(value = "座位数：区间开始", required = true)
    private Integer seatNumStart;
	
	/**
     * 座位数：区间结束
     */
	@ApiModelProperty(value = "座位数：区间结束", required = true)
    private Integer seatNumEnd;
	
	/**
	 * 桌台总数
	 */
	@ApiModelProperty(value = "桌台总数", required = true)
	private Integer deskTotal;

	/**
	 * @return the seatNumStart
	 */
	public Integer getSeatNumStart() {
		return seatNumStart;
	}

	/**
	 * @param seatNumStart the seatNumStart to set
	 */
	public void setSeatNumStart(Integer seatNumStart) {
		this.seatNumStart = seatNumStart;
	}

	/**
	 * @return the seatNumEnd
	 */
	public Integer getSeatNumEnd() {
		return seatNumEnd;
	}

	/**
	 * @param seatNumEnd the seatNumEnd to set
	 */
	public void setSeatNumEnd(Integer seatNumEnd) {
		this.seatNumEnd = seatNumEnd;
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
