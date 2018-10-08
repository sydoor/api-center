package com.lizikj.api.vo.merchandise.param;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 美食模板数量总览
 * @author zhoufe 
 * @date 2017年7月11日 下午4:59:12
 */
@ApiModel
public class ChainGoodsSummaryVO extends BaseDTO{

	private static final long serialVersionUID = 1L;

	/**
	 * 美食模板总数
	 */
	@ApiModelProperty(value = "美食模板总数", required = true)
	private Integer total;
	
	/**
	 * 即将删除模板数量
	 */
	@ApiModelProperty(value = "即将删除模板数量", required = true)
	private Integer aboutToDelete;


	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getAboutToDelete() {
		return aboutToDelete;
	}

	public void setAboutToDelete(Integer aboutToDelete) {
		this.aboutToDelete = aboutToDelete;
	}

}
