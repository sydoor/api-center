package com.lizikj.api.vo.merchandise;

import java.util.List;

import com.lizikj.api.vo.merchandise.base.BaseGoodsVO;
import com.lizikj.common.enums.VirtualEnum;
import com.lizikj.merchandise.enums.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author zhoufe 
 * @date 2017年7月12日 上午11:28:16
 */
@ApiModel
public class GoodsVO extends BaseGoodsVO {

	private static final long serialVersionUID = 1L;
	
	/**
     * 总店物品id：自有的美食该字段为空，如果该门店是连锁，关闭自有美食开关，显示禁用
     */
	@ApiModelProperty(value = "总店物品id：自有的美食该字段为空，如果该门店是连锁，关闭自有美食开关，显示禁用", required = true)
    private String chainGoodsId;
    /**
     * 店铺id
     */
	@ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;
    /**
     * 删除状态
     */
	@ApiModelProperty(value = "删除状态", required = true)
    private RemoveStatusEnum removeStatus;
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
     * 所属分类列表
     */
	@ApiModelProperty(value = "所属分类", required = true)
    private List<CategoryVO> categories;

    /**
     * 加料列表
     */
	@ApiModelProperty(value = "加料", required = true)
    private List<SnackVO> snacks;

    /**
     * 所属标签列表
     */
	@ApiModelProperty(value = "所属标签", required = true)
    private List<TagVO> tags;

    /**
     * 库存
     * (如果设置了规格，这里是总库存，SKU库存的改变，此处的库存数据也需要同时设置，这样设计的目的是方便实时统计)
     */
	@ApiModelProperty(value = "库存：如果设置了规格，这里是总库存，SKU库存的改变，此处的库存数据也需要同时设置，这样设计的目的是方便实时统计", required = true)
    private Integer stock;

    /**
	 * 销量
	 * (如果设置了规格，这里是总销量，SKU销量的改变，此处的销量数据也需要同时设置，这样设计的目的是方便实时统计)
	 */
	@ApiModelProperty(value = "如果设置了规格，这里是总销量，SKU销量的改变，此处的销量数据也需要同时设置，这样设计的目的是方便实时统计", required = true)
	private Integer sellVolume;

    /**
     * 计价方式
     */
	@ApiModelProperty(value = "计价方式", required = true)
    private CalcPriceMethodVO calcPriceMethod;
    /**
     * 套餐类型：0.非套餐，1.主次搭配型套餐，2.组合多选型套餐，3.固定双拼方案，4.任意双拼方案
     */
	@ApiModelProperty(value = "套餐类型,NONE, 普通美食;" +
			"    MASTER_SLAVE, 主次搭配型套餐;" +
			"    COMPOSE_MORE_SELECT,组合多选型套餐" +
			"    DOUBLE_FIXED, 固定双拼方案" +
			"    DOUBLE_ANY, 任意双拼方案", required = true)
    private PackageTypeEnum packageType;
    /**
     * sku类型
     */
	@ApiModelProperty(value = "sku类型，NONE, 无;" +
			"    SINGLE, 单一型规格;" +
			"    COMPOSE, 组合型规格;" +
			"    PACKAGE, 套餐型规格;", required = true)
    private SkuTypeEnum skuType;

	/**
	 * 辣度
	 */
	@ApiModelProperty(value = "辣度")
	private ScovilleEnum scoville;

	@ApiModelProperty(value = "美食调价")
	private List<ScheduleDiscountVO> scheduleDiscounts;

	private VirtualEnum virtual;

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
	 * 数量或者重量(OrderGodos|CartGoods中有效),如果是重量，放大了100倍
	 */
	@ApiModelProperty(value = "数量或者重量(OrderGodos|CartGoods中有效),如果是重量，放大了100倍")
	private Integer nums;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer order;

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	/**
	 * @return the chainGoodsId
	 */
	public String getChainGoodsId() {
		return chainGoodsId;
	}

	/**
	 * @param chainGoodsId the chainGoodsId to set
	 */
	public void setChainGoodsId(String chainGoodsId) {
		this.chainGoodsId = chainGoodsId;
	}

	/**
	 * @return the shopId
	 */
	public Long getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/**
	 * @return the removeStatus
	 */
	public RemoveStatusEnum getRemoveStatus() {
		return removeStatus;
	}

	/**
	 * @param removeStatus the removeStatus to set
	 */
	public void setRemoveStatus(RemoveStatusEnum removeStatus) {
		this.removeStatus = removeStatus;
	}

	/**
	 * @return the sellState
	 */
	public SellStateEnum getSellState() {
		return sellState;
	}

	/**
	 * @param sellState the sellState to set
	 */
	public void setSellState(SellStateEnum sellState) {
		this.sellState = sellState;
	}

	/**
	 * @return the shelveState
	 */
	public ShelveStateEnum getShelveState() {
		return shelveState;
	}

	/**
	 * @param shelveState the shelveState to set
	 */
	public void setShelveState(ShelveStateEnum shelveState) {
		this.shelveState = shelveState;
	}

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * @return the sellVolume
	 */
	public Integer getSellVolume() {
		return sellVolume;
	}

	/**
	 * @param sellVolume the sellVolume to set
	 */
	public void setSellVolume(Integer sellVolume) {
		this.sellVolume = sellVolume;
	}

	/**
	 * @return the calcPriceMethod
	 */
	public CalcPriceMethodVO getCalcPriceMethod() {
		return calcPriceMethod;
	}

	/**
	 * @param calcPriceMethod the calcPriceMethod to set
	 */
	public void setCalcPriceMethod(CalcPriceMethodVO calcPriceMethod) {
		this.calcPriceMethod = calcPriceMethod;
	}

	/**
	 * @return the packageType
	 */
	public PackageTypeEnum getPackageType() {
		return packageType;
	}

	/**
	 * @param packageType the packageType to set
	 */
	public void setPackageType(PackageTypeEnum packageType) {
		this.packageType = packageType;
	}

	/**
	 * @return the skuType
	 */
	public SkuTypeEnum getSkuType() {
		return skuType;
	}

	/**
	 * @param skuType the skuType to set
	 */
	public void setSkuType(SkuTypeEnum skuType) {
		this.skuType = skuType;
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

}
