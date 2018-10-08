package com.lizikj.api.vo.merchandise;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 美食数量总览
 * @author zhoufe 
 * @date 2017年7月11日 下午4:59:12
 */
@ApiModel
public class GoodsSummaryVO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 美食总数
	 */
	@ApiModelProperty(value = "美食总数", required = true)
	private Integer total;
	
	/**
	 * 在售数量：上架
	 */
	@ApiModelProperty(value = "在售数量：上架", required = true)
	private Integer onShelveCount;
	/**
	 * 已下架数量
	 */
	@ApiModelProperty(value = "已下架数量", required = true)
	private Integer offShelveCount;
	/**
	 * 售罄数量
	 */
	@ApiModelProperty(value = "售罄数量", required = true)
	private Integer sellOutCount;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getOnShelveCount() {
		return onShelveCount;
	}
	public void setOnShelveCount(Integer onShelveCount) {
		this.onShelveCount = onShelveCount;
	}
	public Integer getOffShelveCount() {
		return offShelveCount;
	}
	public void setOffShelveCount(Integer offShelveCount) {
		this.offShelveCount = offShelveCount;
	}
	public Integer getSellOutCount() {
		return sellOutCount;
	}
	public void setSellOutCount(Integer sellOutCount) {
		this.sellOutCount = sellOutCount;
	}
}
