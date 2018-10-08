package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.*;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author dyh
 * @created at 2017 11 21 15:05
 */
@ApiModel(value = "根据各种条件查询美食")
public class GoodsQueryAllConditionVO extends BaseDTO {

    @ApiModelProperty(value = "美食模板id")
    private String chainGoodsId;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "删除状态,REMOVED, 已完全删除\n" +
            "    RECYCLE, 回收站中\n" +
            "    ACTIVE, 激活可用状态\n" +
            "    INVISIBLE, 不可见")
    private RemoveStatusEnum removeStatus;

    @ApiModelProperty(value = "销售状态,AVAILABLE, 可售\n" +
            "    SELL_OUT, 售罄")
    private SellStateEnum sellState;

    @ApiModelProperty(value = "上下架状态,OFF_LINE, 下架\n" +
            "    ON_LINE, 上架")
    private ShelveStateEnum shelveState;

    @ApiModelProperty(value = "分类名字")
    private String categoryName;
    @ApiModelProperty(value = "加料名字")
    private String snackName;
    @ApiModelProperty(value = "标签名字")
    private String tagName;


    /**
     * 库存大于
     */
    @ApiModelProperty(value = "库存范围查询，库存大于")
    private Integer startStock;

    /**
     * 库存小于
     */
    @ApiModelProperty(value = "库存范围查询，库存小于")
    private Integer endStock;

    @ApiModelProperty(value = "销量范围查询，销量大于")
    private Integer startSellVolume;

    @ApiModelProperty(value = "销量范围查询，销量小于")
    private Integer endSellVolume;

    @ApiModelProperty(value = "计价方式,NUMBER 数量,WEIGHT 重量")
    private CalcMethodEnum calcMethod;

    /**
     * 套餐类型
     */
    @ApiModelProperty(value = "套餐类型，NONE 普通美食 " +
            "    MASTER_SLAVE,主次搭配型套餐  " +
            "    COMPOSE_MORE_SELECT, 组合多选型套餐  " +
            "    DOUBLE_FIXED,固定双拼方案  " +
            "    DOUBLE_ANY, 任意双拼方案")
    private PackageTypeEnum packageType;
    /**
     * sku类型
     */
    @ApiModelProperty(value = "sku类型，NONE, 无\n" +
            "    SINGLE,单一型规格\n" +
            "    COMPOSE,组合型规格\n" +
            "    PACKAGE, 套餐型规格")
    private SkuTypeEnum skuType;

    /**
     * 计价方式单位名称
     */
    @ApiModelProperty(value = "计价方式单位名称")
    private String merchandiseUnitName;


    /**
     * goodsId
     */
    @ApiModelProperty(value = "goodsId")
    private String goodsId;

    /**
     * 物品名称
     */
    @ApiModelProperty(value = "物品名称")
    private String goodsName;
    /**
     * 物品描述
     */
    @ApiModelProperty(value = "物品描述")
    private String description;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    private String alias;
    /**
     * 物品编号
     */
    @ApiModelProperty(value = "物品编号")
    private String goodsNumber;


    /**
     * 出售价格(单位分)
     */
    @ApiModelProperty(value = "销售价格范围查询")
    private Long startSellPrice;

    @ApiModelProperty(value = "销售价格范围查询")
    private Long endSellPrice;

    /**
     * 会员出售价格(单位分)
     */
    @ApiModelProperty(value = "会员价范围查询")
    private Long startMemberPrice;

    @ApiModelProperty(value = "会员价范围查询")
    private Long endMemberPrice;

    /**
     * 成本价(单位分)
     */
    @ApiModelProperty(value = "成本价范围查询")
    private Long startCostPrice;

    @ApiModelProperty(value = "成本价范围查询")
    private Long endCostPrice;


    /**
     * 是否查询 美食加入的套餐
     */
    @ApiModelProperty(value = "是否查询 美食加入的套餐")
    private boolean joinedPackage;

    /**
     * 是否查询 美食加入的双拼
     */
    @ApiModelProperty(value = "是否查询 美食加入的双拼")
    private boolean joinedDouble;

    @ApiModelProperty(value = "创建时间范围查询")
    private Date startCreateTime;

    @ApiModelProperty(value = "创建时间范围查询")
    private Date endCreateTime;

    public String getChainGoodsId() {
        return chainGoodsId;
    }

    public void setChainGoodsId(String chainGoodsId) {
        this.chainGoodsId = chainGoodsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSnackName() {
        return snackName;
    }

    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getStartStock() {
        return startStock;
    }

    public void setStartStock(Integer startStock) {
        this.startStock = startStock;
    }

    public Integer getEndStock() {
        return endStock;
    }

    public void setEndStock(Integer endStock) {
        this.endStock = endStock;
    }

    public Integer getStartSellVolume() {
        return startSellVolume;
    }

    public void setStartSellVolume(Integer startSellVolume) {
        this.startSellVolume = startSellVolume;
    }

    public Integer getEndSellVolume() {
        return endSellVolume;
    }

    public void setEndSellVolume(Integer endSellVolume) {
        this.endSellVolume = endSellVolume;
    }

    public CalcMethodEnum getCalcMethod() {
        return calcMethod;
    }

    public void setCalcMethod(CalcMethodEnum calcMethod) {
        this.calcMethod = calcMethod;
    }

    public PackageTypeEnum getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageTypeEnum packageType) {
        this.packageType = packageType;
    }

    public SkuTypeEnum getSkuType() {
        return skuType;
    }

    public void setSkuType(SkuTypeEnum skuType) {
        this.skuType = skuType;
    }

    public String getMerchandiseUnitName() {
        return merchandiseUnitName;
    }

    public void setMerchandiseUnitName(String merchandiseUnitName) {
        this.merchandiseUnitName = merchandiseUnitName;
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

    public Long getStartSellPrice() {
        return startSellPrice;
    }

    public void setStartSellPrice(Long startSellPrice) {
        this.startSellPrice = startSellPrice;
    }

    public Long getEndSellPrice() {
        return endSellPrice;
    }

    public void setEndSellPrice(Long endSellPrice) {
        this.endSellPrice = endSellPrice;
    }

    public Long getStartMemberPrice() {
        return startMemberPrice;
    }

    public void setStartMemberPrice(Long startMemberPrice) {
        this.startMemberPrice = startMemberPrice;
    }

    public Long getEndMemberPrice() {
        return endMemberPrice;
    }

    public void setEndMemberPrice(Long endMemberPrice) {
        this.endMemberPrice = endMemberPrice;
    }

    public Long getStartCostPrice() {
        return startCostPrice;
    }

    public void setStartCostPrice(Long startCostPrice) {
        this.startCostPrice = startCostPrice;
    }

    public Long getEndCostPrice() {
        return endCostPrice;
    }

    public void setEndCostPrice(Long endCostPrice) {
        this.endCostPrice = endCostPrice;
    }

    public boolean isJoinedPackage() {
        return joinedPackage;
    }

    public void setJoinedPackage(boolean joinedPackage) {
        this.joinedPackage = joinedPackage;
    }

    public boolean isJoinedDouble() {
        return joinedDouble;
    }

    public void setJoinedDouble(boolean joinedDouble) {
        this.joinedDouble = joinedDouble;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
