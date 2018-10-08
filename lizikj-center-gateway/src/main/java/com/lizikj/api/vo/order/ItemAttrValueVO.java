package com.lizikj.api.vo.order;

import com.lizikj.merchandise.enums.CalcMethodEnum;
import com.lizikj.order.enums.AttrCodeEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 属性的值
 * Created by adept on 2017/7/12.
 */
@ApiModel
public class ItemAttrValueVO implements Serializable {

    private static final long serialVersionUID = 9172714151348171210L;

    @ApiModelProperty(value = "商品属性的值ID：SkuValue id或者子商品的ID，加料ID，口味ID，AttrValueRegardedEnum的code", required = true)
    private String attrValueId;

    @ApiModelProperty(value = "属性值名称：SkuValue value或者子商品的名称，加料名字，口味名字，AttrValueRegardedEnum的description", required = true)
    private String attrValueName;

    @ApiModelProperty(value = "属性代码：见AttrCodeEnum：SKU.规格，PACKAGE.套餐，COOKING_METHOD.做法，FLAVOR.口味，CUSTOM_REMARK，备注", required = true)
    private AttrCodeEnum attrCode;

    @ApiModelProperty(value = "单价", required = true)
    private Long saleAmount;

    @ApiModelProperty(value = "单位名称", required = true)
    private String unitName;

    @ApiModelProperty(value = "个数", required = true)
    private Long sellVolume;

    @ApiModelProperty(value = "总价", required = true)
    private Long totalAmount;

    @ApiModelProperty(value = "激活的SKU描述：activeSkuId 包含的各个规格项的名字，按格式拼装起来，如：（冷/大杯）", required = true)
    private String activeSkuDesc;

    @ApiModelProperty(value = "如果是套餐的value记录加入的是记数或者记重商品：见CalcMethodEnum： NUMBER.数量，WEIGHT.重量。", required = true)
    private CalcMethodEnum calcMethod;

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

    public String getActiveSkuDesc() {
        return activeSkuDesc;
    }

    public void setActiveSkuDesc(String activeSkuDesc) {
        this.activeSkuDesc = activeSkuDesc;
    }

    public CalcMethodEnum getCalcMethod() {
        return calcMethod;
    }

    public void setCalcMethod(CalcMethodEnum calcMethod) {
        this.calcMethod = calcMethod;
    }
}
