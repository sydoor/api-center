package com.lizikj.api.vo.order;

import com.lizikj.order.enums.AttrCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 商品属性：子商品，规格，做法
 * Created by adept on 2017/7/12.
 */
@ApiModel
public class ShoppingCartGoodsAttrVO {

    @ApiModelProperty(value = "商品属性ID：SkuProperty id或者是AttrRegardedEnum里面的code", required = true)
    private String attrId;

    @ApiModelProperty(value = "属性名称：SkuProperty name或者是AttrRegardedEnum里面的description", required = true)
    private String attrName;

    @ApiModelProperty(value = "属性代码：见AttrCodeEnum：SKU.规格，PACKAGE.套餐，COOKING_METHOD.做法，FLAVOR.口味，REMARK，备注", required = true)
    private AttrCodeEnum attrCode;

    @ApiModelProperty(value = "商品属性值", required = true)
    private List<ShoppingCartGoodsAttrValueVO> goodsAttrValueList;


    public List<ShoppingCartGoodsAttrValueVO> getGoodsAttrValueList() {
        return goodsAttrValueList;
    }

    public void setGoodsAttrValueList(List<ShoppingCartGoodsAttrValueVO> goodsAttrValueList) {
        this.goodsAttrValueList = goodsAttrValueList;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public AttrCodeEnum getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(AttrCodeEnum attrCode) {
        this.attrCode = attrCode;
    }
}
