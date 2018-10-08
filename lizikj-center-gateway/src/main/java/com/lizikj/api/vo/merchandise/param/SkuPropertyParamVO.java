package com.lizikj.api.vo.merchandise.param;

import java.util.List;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 规格属性
 *
 * @author Michael.Huang
 * @date 2017/6/22
 */
@ApiModel
public class SkuPropertyParamVO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
     * id
     */
	@ApiModelProperty(value = "id", required = true)
    private Integer id;

    /**
     * 物品id
     */
    private String goodsId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 排序
     */
	@ApiModelProperty(value = "排序", required = true)
    private Integer order;

    /**
     * 规格名称
     */

	@ApiModelProperty(value = "规格名称", required = true)
    private String name;

    /**
     * 规格项
     */
	@ApiModelProperty(value = "规格项", required = true)
    private List<SkuValueUpdateParamVO> values;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public List<SkuValueUpdateParamVO> getValues() {
		return values;
	}

	public void setValues(List<SkuValueUpdateParamVO> values) {
		this.values = values;
	}
    
}
