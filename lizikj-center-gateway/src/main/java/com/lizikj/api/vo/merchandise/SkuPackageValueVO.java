package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ASUS
 * @created at 2017 08 04 17:59
 */
@ApiModel
public class SkuPackageValueVO {
    /**
     * 物品Id
     */
    @ApiModelProperty(value = "物品Id", required = true)
    private String goodsId;

    /**
     * 总店物品id：自有的美食该字段为空，如果该门店是连锁，关闭自有美食开关，显示禁用
     */
    @ApiModelProperty(value = "总店物品id：自有的美食该字段为空，如果该门店是连锁，关闭自有美食开关，显示禁用")
    private String chainGoodsId;

    /**
     * 物品名称
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private String goodsName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    private Integer order;

    /**
     * 计价方式
     */
    @ApiModelProperty(value = "计价方式", required = true)
    private CalcPriceMethodVO calcPriceMethod;

    /**
     * 做法：加入套餐时选中的那个做法
     */
    @ApiModelProperty(value = "做法")
    private String cookingMethod;

    /**
     * 计数(如果是计数，此处表示数量，如果是记重菜，此处表示重量，具体可参考物品计数单位)
     */
    @ApiModelProperty(value = "计数(如果是计数，此处表示数量，如果是记重菜，此处表示重量，具体可参考物品计数单位)", required = true)
    private Long count;

    /**
     * 出售价格(单位分)
     */
    @ApiModelProperty(value = "出售价格(单位分)", required = true)
    private Long sellPrice;

    /**
     * 激活的SKU
     */
    @ApiModelProperty(value = "激活的SKU：如果套餐时多规格的只有选一个规格加入套餐", required = true)
    private Long activeSkuId;

    /**
     * 激活的SKU描述：activeSkuId 包含的各个规格项的名字，按格式拼装起来，如：（冷/大杯）
     */
    @ApiModelProperty(value = "激活的SKU描述：activeSkuId 包含的各个规格项的名字，按格式拼装起来，如：（冷/大杯）", required = true)
    private String activeSkuDesc;

    public String getChainGoodsId() {
        return chainGoodsId;
    }

    public void setChainGoodsId(String chainGoodsId) {
        this.chainGoodsId = chainGoodsId;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }


    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getActiveSkuId() {
        return activeSkuId;
    }

    public void setActiveSkuId(Long activeSkuId) {
        this.activeSkuId = activeSkuId;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    public String getActiveSkuDesc() {
        return activeSkuDesc;
    }

    public void setActiveSkuDesc(String activeSkuDesc) {
        this.activeSkuDesc = activeSkuDesc;
    }
}
