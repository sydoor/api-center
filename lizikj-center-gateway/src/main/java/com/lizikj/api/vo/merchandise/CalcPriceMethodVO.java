package com.lizikj.api.vo.merchandise;

import com.lizikj.merchandise.enums.CalcMethodEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/** 
 * @author zhoufe 
 * @date 2017年7月19日 下午5:45:49 
 */
@ApiModel
public class CalcPriceMethodVO {
	
	/**
	 * 计价方式ID：见CalcMethodEnum
	 */
	@ApiModelProperty(value = "计价方式ID：1.按数量计价，2.按重量计价")
	private CalcMethodEnum calcMethod;
	
	/**
	 * 计价方式单位：见店铺里自定义单位
	 */
	@ApiModelProperty(value = "计价方式单位ID：见店铺里自定义单位")
	private Long merchandiseUnitId;

	@ApiModelProperty(value = "计价方式单位名称：见店铺里自定义单位")
	private String merchandiseUnitName;

	public CalcMethodEnum getCalcMethod() {
		return calcMethod;
	}

	public void setCalcMethod(CalcMethodEnum calcMethod) {
		this.calcMethod = calcMethod;
	}

	/**
	 * @return the merchandiseUnitId
	 */
	public Long getMerchandiseUnitId() {
		return merchandiseUnitId;
	}

	/**
	 * @param merchandiseUnitId the merchandiseUnitId to set
	 */
	public void setMerchandiseUnitId(Long merchandiseUnitId) {
		this.merchandiseUnitId = merchandiseUnitId;
	}

	public String getMerchandiseUnitName() {
		return merchandiseUnitName;
	}

	public void setMerchandiseUnitName(String merchandiseUnitName) {
		this.merchandiseUnitName = merchandiseUnitName;
	}
}
