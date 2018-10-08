package com.lizikj.api.vo.merchandise.param;

import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michael.Huang
 * @date 2017/6/23
 */
@ApiModel
public class SkuValueUpdateParamVO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	/**
     * id
     */
	@ApiModelProperty(value = "id", required = true)
    private Integer id;

    /**
     * 值
     */
	@ApiModelProperty(value = "值", required = true)
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
