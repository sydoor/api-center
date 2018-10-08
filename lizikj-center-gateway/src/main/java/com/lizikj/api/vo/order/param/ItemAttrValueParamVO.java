package com.lizikj.api.vo.order.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Michael.Huang
 * @date 2017/8/25 15:36
 */
@ApiModel
public class ItemAttrValueParamVO implements Serializable {

    private static final long serialVersionUID = 7547896821276682849L;

    @ApiModelProperty(value = "商品属性的值ID：加料用mongodb的ID，口味就用mysql的ID或者AttrValueRegardedEnum，-77703：自定义备注，-77704：做法。（有ID的不需要传orderItemAttrValueName）", required = true)
    private String attrValueId;

    @ApiModelProperty(value = "属性值名称：自定义备注的文字，做法的文字", required = true)
    private String attrValueName;


    public String getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(String attrValueId) {
        this.attrValueId = attrValueId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }
}
