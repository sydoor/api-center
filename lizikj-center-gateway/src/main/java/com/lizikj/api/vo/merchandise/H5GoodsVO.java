package com.lizikj.api.vo.merchandise;

import com.lizikj.merchandise.enums.ScovilleEnum;
import com.lizikj.merchandise.enums.SellStateEnum;
import com.lizikj.merchandise.enums.ShelveStateEnum;
import com.lizikj.merchandise.enums.SkuTypeEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Set;

/**
 * @author dyh
 * @created at 2017 09 01 9:46
 */
@ApiModel
public class H5GoodsVO extends BaseDTO{

    /**
     * goodsId
     */
    private String id;
    /**
     * 物品名称
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private String goodsName;

    /**
     * 所属标签列表
     */
    @ApiModelProperty(value = "所属标签", required = true)
    private List<TagVO> tags;

    /**
     * 图片列表
     */
    @ApiModelProperty(value = "图片列表", required = true)
    private List<Long> mediaIds;

    /**
     * 出售价格(单位分)
     */
    @ApiModelProperty(value = "出售价格(单位分)", required = true)
    private Long sellPrice;

    /**
     * 会员出售价格(单位分)
     */
    @ApiModelProperty(value = "会员出售价格(单位分)", required = true)
    private Long memberPrice;

    @ApiModelProperty(value = "是否有会员优惠",dataType = "Boolean")
    private boolean memberFavorable;

    /**
     * 库存
     * (如果设置了规格，这里是总库存，SKU库存的改变，此处的库存数据也需要同时设置，这样设计的目的是方便实时统计)
     */
    @ApiModelProperty(value = "库存：如果设置了规格，这里是总库存，SKU库存的改变，此处的库存数据也需要同时设置，这样设计的目的是方便实时统计", required = true)
    private Integer stock;

    @ApiModelProperty(value = "过去30天的销量")
    private Integer last30DaysSellVolume;

    /**
     * 辣度
     */
    @ApiModelProperty(value = "辣度")
    private ScovilleEnum scoville;

    public ScovilleEnum getScoville() {
        return scoville;
    }

    public void setScoville(ScovilleEnum scoville) {
        this.scoville = scoville;
    }

    public Integer getLast30DaysSellVolume() {
        return last30DaysSellVolume;
    }

    public void setLast30DaysSellVolume(Integer last30DaysSellVolume) {
        this.last30DaysSellVolume = last30DaysSellVolume;
    }

    /**
     * 计价方式
     */
    @ApiModelProperty(value = "计价方式", required = true)
    private CalcPriceMethodVO calcPriceMethod;

    /**
     * 销售状态
     */
    @ApiModelProperty(value = "销售状态", required = true)
    private SellStateEnum sellState;
    /**
     * 上架状态
     */
    @ApiModelProperty(value = "上架状态", required = true)
    private ShelveStateEnum shelveState;

    /**
     * 加入的活动列表
     */
    @ApiModelProperty(value = "加入的活动列表", required = true)
    private Set<ActivityInfoVO> joinedActivityIds;

    /**
     * sku类型
     */
    @ApiModelProperty(value = "sku类型，NONE, 无;" +
            "    SINGLE, 单一型规格;" +
            "    COMPOSE, 组合型规格;" +
            "    PACKAGE, 套餐型规格;", required = true)
    private SkuTypeEnum skuType;

    /**
     * 物品描述
     */
    @ApiModelProperty(value = "物品描述", required = true)
    private String description;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名", required = true)
    private String alias;

    /**
     * sku列表
     */
    @ApiModelProperty(value = "sku列表", required = true)
    private List<SkuVO> skuList;

    /**
     * 做法
     */
    @ApiModelProperty(value = "做法", required = true)
    private List<CookingMethodVO> cookingMethods;

    /**
     * 加料
     */
    @ApiModelProperty(value = "加料", required = true)
    private List<SnackVO> snacks;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public List<SnackVO> getSnacks() {
        return snacks;
    }

    public void setSnacks(List<SnackVO> snacks) {
        this.snacks = snacks;
    }

    public boolean isMemberFavorable() {
        return memberFavorable;
    }

    public void setMemberFavorable(boolean memberFavorable) {
        this.memberFavorable = memberFavorable;
    }

    public SkuTypeEnum getSkuType() {
        return skuType;
    }

    public void setSkuType(SkuTypeEnum skuType) {
        this.skuType = skuType;
    }

    public Set<ActivityInfoVO> getJoinedActivityIds() {
        return joinedActivityIds;
    }

    public void setJoinedActivityIds(Set<ActivityInfoVO> joinedActivityIds) {
        this.joinedActivityIds = joinedActivityIds;
    }

    public SellStateEnum getSellState() {
        return sellState;
    }

    public void setSellState(SellStateEnum sellState) {
        this.sellState = sellState;
    }

    public ShelveStateEnum getShelveState() {
        return shelveState;
    }

    public void setShelveState(ShelveStateEnum shelveState) {
        this.shelveState = shelveState;
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

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }

    public List<Long> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<Long> mediaIds) {
        this.mediaIds = mediaIds;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public CalcPriceMethodVO getCalcPriceMethod() {
        return calcPriceMethod;
    }

    public void setCalcPriceMethod(CalcPriceMethodVO calcPriceMethod) {
        this.calcPriceMethod = calcPriceMethod;
    }
}