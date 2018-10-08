package com.lizikj.api.vo.order;

import com.lizikj.order.enums.AttrCodeEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 属性的值
 * Created by adept on 2017/7/12.
 */
@ApiModel
public class ShoppingCartGoodsAttrValueVO {


    @ApiModelProperty(value = "商品属性的值ID：SkuValue id或者子商品的ID，加料ID，口味ID，AttrValueRegardedEnum的code", required = true)
    private Long attrValueId;

    @ApiModelProperty(value = "属性值名称：SkuValue value或者子商品的名称，加料名字，口味名字，AttrValueRegardedEnum的description", required = true)
    private String attrValueName;

    @ApiModelProperty(value = "属性代码：见AttrCodeEnum：SKU.规格，PACKAGE.套餐，COOKING_METHOD.做法，FLAVOR.口味，REMARK，备注", required = true)
    private AttrCodeEnum attrCode;

    @ApiModelProperty(value = "单价", required = true)
    private Long saleAmount;

    @ApiModelProperty(value = "单位名称", required = true)
    private String unitName;

    @ApiModelProperty(value = "个数", required = true)
    private Long sellVolume;

    @ApiModelProperty(value = "总价", required = true)
    private Long totalAmount;

    public Long getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    public Long getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Long saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(Long sellVolume) {
        this.sellVolume = sellVolume;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public AttrCodeEnum getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(AttrCodeEnum attrCode) {
        this.attrCode = attrCode;
    }
}
