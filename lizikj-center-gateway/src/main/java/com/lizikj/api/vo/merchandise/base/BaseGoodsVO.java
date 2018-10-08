package com.lizikj.api.vo.merchandise.base;

import com.lizikj.api.vo.merchandise.ActivityInfoVO;
import com.lizikj.api.vo.merchandise.CookingMethodVO;
import com.lizikj.api.vo.merchandise.SkuPropertyVO;
import com.lizikj.api.vo.merchandise.SkuVO;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Set;
import java.util.Date;

/**
 * 物品基类
 *
 * @author Michael.Huang
 * @date 2017/6/23
 */
@ApiModel
public class BaseGoodsVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private String id;

    /**
     * 物品名称
     */
    @ApiModelProperty(value = "物品名称", required = true)
    private String goodsName;
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
     * 物品编号
     */
    @ApiModelProperty(value = "物品编号", required = true)
    private String goodsNumber;
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

    /**
     * 成本价(单位分)
     */
    @ApiModelProperty(value = "成本价(单位分)", required = true)
    private Long costPrice;

    /**
     * sku属性列表
     */
    @ApiModelProperty(value = "sku属性列表", required = true)
    private List<SkuPropertyVO> skuPropertyList;
    /**
     * sku列表
     */
    @ApiModelProperty(value = "sku列表", required = true)
    private List<SkuVO> skuList;

    /**
     * 加入的套餐物品列表
     */
    @ApiModelProperty(value = "加入的套餐物品列表", required = true)
    private Set<String> joinedPackageIds;
    /**
     * 加入的双拼物品列表
     */
    @ApiModelProperty(value = "加入的双拼物品列表", required = true)
    private Set<String> joinedDoubleIds;
    /**
     * 加入的活动列表
     */
    @ApiModelProperty(value = "加入的活动列表", required = true)
    private Set<ActivityInfoVO> joinedActivityIds;

    /**
     * 做法
     */
    @ApiModelProperty(value = "做法", required = true)
    private List<CookingMethodVO> cookingMethods;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    /**
     * 是否要打印的美食
     */
    @ApiModelProperty(value = "是否要打印的美食", required = true)
    @Deprecated
    private Boolean printEnabled;

    /**
     * 是否无需打印的美食
     */
    @ApiModelProperty(value = "是否无需打印的美食", required = true)
    private Boolean donotPrint;

    /**
     * 销售提成金额*100
     */
    @ApiModelProperty(value = "销售提成金额*100", required = true)
    private Long salesExtractAmount;

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

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
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

    public Set<String> getJoinedPackageIds() {
        return joinedPackageIds;
    }

    public void setJoinedPackageIds(Set<String> joinedPackageIds) {
        this.joinedPackageIds = joinedPackageIds;
    }

    public Set<String> getJoinedDoubleIds() {
        return joinedDoubleIds;
    }

    public void setJoinedDoubleIds(Set<String> joinedDoubleIds) {
        this.joinedDoubleIds = joinedDoubleIds;
    }

    public Set<ActivityInfoVO> getJoinedActivityIds() {
        return joinedActivityIds;
    }

    public void setJoinedActivityIds(Set<ActivityInfoVO> joinedActivityIds) {
        this.joinedActivityIds = joinedActivityIds;
    }

    public List<CookingMethodVO> getCookingMethods() {
        return cookingMethods;
    }

    public void setCookingMethods(List<CookingMethodVO> cookingMethods) {
        this.cookingMethods = cookingMethods;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getPrintEnabled() {
        return printEnabled;
    }

    public void setPrintEnabled(Boolean printEnabled) {
        this.printEnabled = printEnabled;
    }

    public Long getSalesExtractAmount() {
        return salesExtractAmount;
    }

    public void setSalesExtractAmount(Long salesExtractAmount) {
        this.salesExtractAmount = salesExtractAmount;
    }

    public Boolean getDonotPrint() {
        return donotPrint;
    }

    public void setDonotPrint(Boolean donotPrint) {
        this.donotPrint = donotPrint;
    }
}
