package com.lizikj.api.vo.merchandise.param;

import com.lizikj.api.vo.merchandise.*;
import com.lizikj.common.enums.VirtualEnum;
import com.lizikj.merchandise.enums.*;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Set;

/**
 * @author zhoufe
 * @date 2017/6/17
 */
@ApiModel
public class GoodsParamVO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	/**
	 * 商品ID
	 */
	@ApiModelProperty(value = "商品ID,新增时不用传", required = true)
	private String id;
	/**
	 * 总店物品id
	 */
	@ApiModelProperty(value = "美食模板ID,新增连锁店美食时传", required = true)
	private String chainGoodsId;
	/**
	 * 店铺ID
	 */
	@ApiModelProperty(value = "店铺ID", required = true)
	private Long shopId;
	/**
	 * 美食名称
	 */
	@ApiModelProperty(value = "美食名称", required = true)
	private String goodsName;
	/**
	 * 成本价（单位分）
	 */
	@ApiModelProperty(value = "成本价（单位分）", required = true)
	private Long costPrice;
	/**
	 * 销售价（单位分）
	 */
	@ApiModelProperty(value = "销售价（单位分）", required = true)
	private Long sellPrice;
	
	/**
	 * 会员价（单位分
	 */
	@ApiModelProperty(value = "会员价（单位分）", required = true)
	private Long memberPrice;
	
	/**
	 * 套餐类型：见PackageTypeEnum
	 */
	@ApiModelProperty(value = "套餐类型：见PackageTypeEnum", required = true)
	private PackageTypeEnum packageType;
	
	/**
	 * 上下架状态：见ShelveStateEnum
	 */
	@ApiModelProperty(value = "上下架状态：见ShelveStateEnum", required = true)
    private ShelveStateEnum shelveState;

	/**
	 * 销售状态
	 */
	private SellStateEnum sellState;
    
    /**
     * 描述
     */
	@ApiModelProperty(value = "描述", required = true)
    private String description;
    
    /**
     * 美食别名
     */
	@ApiModelProperty(value = "美食别名", required = true)
    private String alias;
	
	 /**
     * 物品编号
     */
	@ApiModelProperty(value = "商品编号", required = true)
    private String goodsNumber;
    /**
     * 图片列表
     */
	@ApiModelProperty(value = "图片列表", required = true)
    private List<Long> mediaIds;
    
    /**
     * 计价方式：1.用按数量计价/个，2.按斤量计价/位
     */
	@ApiModelProperty(value = "计价方式：如，按数量计价/自定义单位", required = true)
    private CalcPriceMethodVO calcPriceMethod;
	
    /**
     * 规格（SKU）类型：SkuTypeEnum
     */
	@ApiModelProperty(value = "规格（SKU）类型：SkuTypeEnum", required = true)
    private SkuTypeEnum skuType;

	/**
	 * 辣度
	 */
	@ApiModelProperty(value = "辣度")
	private ScovilleEnum scoville;

	private VirtualEnum virtual;

	@ApiModelProperty(value = "美食调价")
	private List<ScheduleDiscountVO> scheduleDiscounts;

	/**
	 * 是否要打印的美食
	 */
	@Deprecated
	@ApiModelProperty(value = "是否要打印的美食", required = true)
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

	public List<ScheduleDiscountVO> getScheduleDiscounts() {
		return scheduleDiscounts;
	}

	public void setScheduleDiscounts(List<ScheduleDiscountVO> scheduleDiscounts) {
		this.scheduleDiscounts = scheduleDiscounts;
	}

	public VirtualEnum getVirtual() {
		return virtual;
	}

	public void setVirtual(VirtualEnum virtual) {
		this.virtual = virtual;
	}

	public ScovilleEnum getScoville() {
		return scoville;
	}

	public void setScoville(ScovilleEnum scoville) {
		this.scoville = scoville;
	}

    /**
     * 规格（SKU组）
     */
	@ApiModelProperty(value = "规格（SKU组）", required = true)
    private List<SkuPropertyVO> skuPropertyList;

	/**
	 * 销量
	 * (如果设置了规格，这里是总销量，SKU销量的改变，此处的销量数据也需要同时设置，这样设计的目的是方便实时统计)
	 */
	@ApiModelProperty(value = "如果设置了规格，这里是总销量，SKU销量的改变，此处的销量数据也需要同时设置，这样设计的目的是方便实时统计", required = true)
	private Integer sellVolume;

	public Integer getSellVolume() {
		return sellVolume;
	}

	public void setSellVolume(Integer sellVolume) {
		this.sellVolume = sellVolume;
	}

	/**
     * SKU
     */
	@ApiModelProperty(value = "SKU", required = true)
    private List<SkuVO> skuList;
    
    /**
     * 所属分类
     */
	@ApiModelProperty(value = "所属分类", required = true)
    private List<CategoryVO> categories;

	/**
	 * 加料
	 */
	@ApiModelProperty(value = "加料", required = true)
	private List<SnackVO> snacks;

    /**
     * 所属标签
     */
	@ApiModelProperty(value = "所属标签", required = true)
    private List<TagVO> tags;
    
    /**
     * 做法
     */
	@ApiModelProperty(value = "做法", required = true)
    private List<CookingMethodVO> cookingMethods;

	/**
	 * 库存
	 *
	 */
	@ApiModelProperty(value = "库存", required = true)
	private Integer stock;

	/**
	 * 排序
	 */
	private Integer order;

	/**
	 * 加入的活动列表
	 */
	@ApiModelProperty(value = "加入的活动列表", required = true)
	private Set<ActivityInfoVO> joinedActivityIds;

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
	 * 删除状态
	 */
	@ApiModelProperty(value = "删除状态", required = true)
	private RemoveStatusEnum removeStatus;

	/**
	 * 数量或者重量(OrderGodos|CartGoods中有效),如果是重量，放大了100倍
	 */
	@ApiModelProperty(value = "数量或者重量(OrderGodos|CartGoods中有效),如果是重量，放大了100倍")
	private Integer nums;

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public RemoveStatusEnum getRemoveStatus() {
		return removeStatus;
	}

	public void setRemoveStatus(RemoveStatusEnum removeStatus) {
		this.removeStatus = removeStatus;
	}

	public Set<ActivityInfoVO> getJoinedActivityIds() {
		return joinedActivityIds;
	}

	public void setJoinedActivityIds(Set<ActivityInfoVO> joinedActivityIds) {
		this.joinedActivityIds = joinedActivityIds;
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public ShelveStateEnum getShelveState() {
		return shelveState;
	}
	public void setShelveState(ShelveStateEnum shelveState) {
		this.shelveState = shelveState;
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

	public CalcPriceMethodVO getCalcPriceMethod() {
		return calcPriceMethod;
	}

	public void setCalcPriceMethod(CalcPriceMethodVO calcPriceMethod) {
		this.calcPriceMethod = calcPriceMethod;
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

	public Long getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Long costPrice) {
		this.costPrice = costPrice;
	}
	public Long getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Long sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(Long memberPrice) {
		this.memberPrice = memberPrice;
	}

	/**
	 * @return the goodsNumber
	 */
	public String getGoodsNumber() {
		return goodsNumber;
	}
	/**
	 * @param goodsNumber the goodsNumber to set
	 */
	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	/**
	 * @return the mediaIds
	 */
	public List<Long> getMediaIds() {
		return mediaIds;
	}
	/**
	 * @param mediaIds the mediaIds to set
	 */
	public void setMediaIds(List<Long> mediaIds) {
		this.mediaIds = mediaIds;
	}

	public List<CategoryVO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryVO> categories) {
		this.categories = categories;
	}

	public List<SnackVO> getSnacks() {
		return snacks;
	}

	public void setSnacks(List<SnackVO> snacks) {
		this.snacks = snacks;
	}

	public List<TagVO> getTags() {
		return tags;
	}

	public void setTags(List<TagVO> tags) {
		this.tags = tags;
	}

	public SellStateEnum getSellState() {
		return sellState;
	}

	public void setSellState(SellStateEnum sellState) {
		this.sellState = sellState;
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
