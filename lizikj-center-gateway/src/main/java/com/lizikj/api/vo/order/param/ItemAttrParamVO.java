package com.lizikj.api.vo.order.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/8/25 15:36
 */
@ApiModel
public class ItemAttrParamVO<T extends ItemAttrValueParamVO> implements Serializable {

    private static final long serialVersionUID = 6489971779295732569L;

    @ApiModelProperty(value = "商品属性ID：如果有的没有就填见AttrRegardedEnum：-88801：加料，-88802：口味，-88803：自定义备注，-88804：做法", required = true)
    private String attrId;

    @ApiModelProperty(value = "商品属性值", required = true)
    private List<T> itemAttrValueParamList;

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public List<T> getItemAttrValueParamList() {
        return itemAttrValueParamList;
    }

    public void setItemAttrValueParamList(List<T> itemAttrValueParamList) {
        this.itemAttrValueParamList = itemAttrValueParamList;
    }
}
