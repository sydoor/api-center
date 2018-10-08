package com.lizikj.api.vo.merchandise;

import com.lizikj.merchandise.enums.SkuPropertyTypeEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 规格属性
 *
 * @author Michael.Huang
 * @date 2017/6/22
 */
@ApiModel
public class SkuPropertyVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;

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
     * sku属性类型
     */
    @ApiModelProperty(value = "NORMAL, 普通规格属性" +
            "    COMBO_MASTER, 主要美食规格" +
            "    COMBO_SLAVE, 次要美食规格" +
            "    COMPOSE_MORE_SELECT, 组合多选型套餐" +
            "    DOUBLE_FIXED, 固定双拼方案" +
            "    DOUBLE_ANY, 任意双拼方案")
    private SkuPropertyTypeEnum skuPropertyType;

    /**
     * 套餐规格属性
     */
    private SkuPackagePropertyVO packageProperty;

    /**
     * 规格项
     */
    @ApiModelProperty(value = "规格项", required = true)
    private List<SkuValueVO> values;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SkuPropertyTypeEnum getSkuPropertyType() {
        return skuPropertyType;
    }

    public void setSkuPropertyType(SkuPropertyTypeEnum skuPropertyType) {
        this.skuPropertyType = skuPropertyType;
    }

    public SkuPackagePropertyVO getPackageProperty() {
        return packageProperty;
    }

    public void setPackageProperty(SkuPackagePropertyVO packageProperty) {
        this.packageProperty = packageProperty;
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

    public List<SkuValueVO> getValues() {
        return values;
    }

    public void setValues(List<SkuValueVO> values) {
        this.values = values;
    }
}
