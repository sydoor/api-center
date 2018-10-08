package com.lizikj.api.vo.merchandise;

import com.lizikj.merchandise.enums.SkuTypeEnum;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 09 14 14:15
 */
public class SimpleGoodsVO {
    /**
     * goodsId
     */
    private String id;

    /**
     * 物品名称
     */
    private String goodsName;

    /**
     * 计价方式
     */
    private CalcPriceMethodVO calcPriceMethod;
    /**
     * sku
     */
    private  SkuVO sku;

    private List<String> categoryIds;

    /**
     * sku类型
     */
    private SkuTypeEnum skuType;

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public SkuTypeEnum getSkuType() {
        return skuType;
    }

    public void setSkuType(SkuTypeEnum skuType) {
        this.skuType = skuType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public CalcPriceMethodVO getCalcPriceMethod() {
        return calcPriceMethod;
    }

    public void setCalcPriceMethod(CalcPriceMethodVO calcPriceMethod) {
        this.calcPriceMethod = calcPriceMethod;
    }

    public SkuVO getSku() {
        return sku;
    }

    public void setSku(SkuVO sku) {
        this.sku = sku;
    }
}
