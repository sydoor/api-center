package com.lizikj.api.vo.merchandise;

import com.lizikj.merchandise.enums.SkuTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2018 01 11 16:14
 */
@ApiModel(value = "PC端，美食简要信息")
public class SimpleGoods4PCVO {
    @ApiModelProperty(value = "美食id")
    private String id;

    /**
     * 物品名称
     */
    @ApiModelProperty(value = "物品名称")
    private String goodsName;

    /**
     * 美食模板id
     */
    @ApiModelProperty(value = "美食模板id")
    private String chainGoodsId;

    /**
     * 物品编号
     */
    @ApiModelProperty(value = "物品编号")
    private String goodsNumber;

    /**
     * 出售价格(单位分)
     */
    @ApiModelProperty(value = "出售价格(单位分)")
    private Long sellPrice;

    /**
     * 会员出售价格(单位分)
     */
    @ApiModelProperty(value = "会员出售价格(单位分)")
    private Long memberPrice;

    /**
     * 成本价(单位分)
     */
    @ApiModelProperty(value = "成本价(单位分)")
    private Long costPrice;

    /**
     * sku属性列表
     */
    @ApiModelProperty(value = "sku属性列表")
    private List<SkuPropertyVO> skuPropertyList;
    /**
     * sku列表
     */
    @ApiModelProperty(value = "sku列表")
    private List<SkuVO> skuList;

    /**
     * 做法
     */
    @ApiModelProperty(value = "做法")
    private List<CookingMethodVO> cookingMethods;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer order;
    /**
     * 计价方式
     */
    @ApiModelProperty(value = "计价方式")
    private CalcPriceMethodVO calcPriceMethod;

    /**
     * sku类型
     */
    @ApiModelProperty(value = "sku类型")
    private SkuTypeEnum skuType;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private List<TagVO> tags;

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

    public String getChainGoodsId() {
        return chainGoodsId;
    }

    public void setChainGoodsId(String chainGoodsId) {
        this.chainGoodsId = chainGoodsId;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Long getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Long memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

    public List<SkuPropertyVO> getSkuPropertyList() {
        return skuPropertyList;
    }

    public void setSkuPropertyList(List<SkuPropertyVO> skuPropertyList) {
        this.skuPropertyList = skuPropertyList;
    }

    public List<SkuVO> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuVO> skuList) {
        this.skuList = skuList;
    }

    public List<CookingMethodVO> getCookingMethods() {
        return cookingMethods;
    }

    public void setCookingMethods(List<CookingMethodVO> cookingMethods) {
        this.cookingMethods = cookingMethods;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public CalcPriceMethodVO getCalcPriceMethod() {
        return calcPriceMethod;
    }

    public void setCalcPriceMethod(CalcPriceMethodVO calcPriceMethod) {
        this.calcPriceMethod = calcPriceMethod;
    }

    public SkuTypeEnum getSkuType() {
        return skuType;
    }

    public void setSkuType(SkuTypeEnum skuType) {
        this.skuType = skuType;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }
}