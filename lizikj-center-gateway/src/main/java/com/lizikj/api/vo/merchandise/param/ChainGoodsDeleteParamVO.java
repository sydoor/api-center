package com.lizikj.api.vo.merchandise.param;

import java.util.List;

import com.lizikj.merchandise.enums.ChainGoodsDeleteTypeEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author zhoufe 
 * @date 2017年7月12日 下午4:46:59
 */
@ApiModel
public class ChainGoodsDeleteParamVO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 删除类型
	 */
	@ApiModelProperty(value = "删除类型：见ChainGoodsDeleteTypeEnum", required = true)
	private ChainGoodsDeleteTypeEnum  deleteType;
    
	/**
	 * 删除的美食模板
	 */
	@ApiModelProperty(value = "删除的美食模板", required = true)
	private List<String> chainGoodsIds;
	
	public ChainGoodsDeleteTypeEnum getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(ChainGoodsDeleteTypeEnum deleteType) {
		this.deleteType = deleteType;
	}

	public List<String> getChainGoodsIds() {
		return chainGoodsIds;
	}

	public void setChainGoodsIds(List<String> chainGoodsIds) {
		this.chainGoodsIds = chainGoodsIds;
	}

}
