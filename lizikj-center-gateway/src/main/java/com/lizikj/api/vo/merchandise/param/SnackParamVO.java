package com.lizikj.api.vo.merchandise.param;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author zhoufe 
 * @date 2017年7月12日 上午11:43:43
 */
@ApiModel
public class SnackParamVO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	/**
     * 店铺id
     */
	@ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;
	@ApiModelProperty(value = "ID,新增时不用传", required = true)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
     * 名字
     */
	@ApiModelProperty(value = "名字", required = true)
    private String name;

    /**
     * 价格(单位分)
     */
	@ApiModelProperty(value = "价格(单位分)", required = true)
    private Long price;


	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
    
}
