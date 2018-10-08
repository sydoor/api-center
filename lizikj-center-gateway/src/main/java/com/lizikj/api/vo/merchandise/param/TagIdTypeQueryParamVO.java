package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.BelongTypeEnum;
import com.lizikj.merchandise.enums.YesOrNoEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author zhoufe 
 * @date 2017年7月14日 上午11:21:54
 */
@ApiModel
public class TagIdTypeQueryParamVO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 标签ID
	 */
	@ApiModelProperty(value = "标签ID", required = true)
	private String tagId;
	/**
	 * 归属：属于该标签的，不属于该标签的
	 */
	@ApiModelProperty(value = "归属：属于该标签的，不属于该标签的", required = true)
	private BelongTypeEnum belongType;

	@ApiModelProperty(value = "是否需要计算分时价格",required = false)
	private YesOrNoEnum needComputeSpecialDiscount;

	public YesOrNoEnum getNeedComputeSpecialDiscount() {
		return needComputeSpecialDiscount;
	}

	public void setNeedComputeSpecialDiscount(YesOrNoEnum needComputeSpecialDiscount) {
		this.needComputeSpecialDiscount = needComputeSpecialDiscount;
	}

	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public BelongTypeEnum getBelongType() {
		return belongType;
	}
	public void setBelongType(BelongTypeEnum belongType) {
		this.belongType = belongType;
	}
	
	
}
