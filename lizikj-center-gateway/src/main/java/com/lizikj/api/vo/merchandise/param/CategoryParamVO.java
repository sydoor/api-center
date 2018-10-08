package com.lizikj.api.vo.merchandise.param;

import com.lizikj.common.enums.VirtualEnum;
import com.lizikj.merchandise.enums.CategoryCodeEnum;
import com.lizikj.merchandise.enums.CategoryTypeEnum;
import com.lizikj.version.dto.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 
 * @author zhoufe 
 * @date 2017年7月11日 下午8:15:35
 */
@ApiModel
public class CategoryParamVO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 分类ID
	 */
	@ApiModelProperty(value = "分类ID", required = true)
	private String id;
	
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称")
	private String name;

	/**
	 * 分类编码，部分固定分类需要统一编码，没有编码的说明是自定义分类
	 * 此处不使用id来区分，因为在迁移数据时可能会变
	 */
	@ApiModelProperty(value = "分类编码")
	private CategoryCodeEnum code;
	
	/**
	 * 分类排序
	 */
	@ApiModelProperty(value = "分类排序")
	private Integer order;

	/**
	 * 店铺ID：门店自己创建时有值，否则无值
	 */
	@ApiModelProperty(value = "店铺ID：门店自己创建时有值，否则无值")
	private Long shopId;


	/**
	 * 商户ID：连锁建分类时赋值
	 */
	@ApiModelProperty(value = "商户ID：连锁建分类时赋值")
	private Long merchantId;

	/**
	 * 类型：1.连锁的分类，2.门店自有的分类
	 */
	@ApiModelProperty(value = "类型：1.连锁的分类，2.门店自有的分类", required = true)
	private CategoryTypeEnum categoryType;

	/**
	 * 该分类下有的商品ID
	 */
	@ApiModelProperty(value = "该分类下有的商品ID", required = true)
	List<String> goodsIds;

	private VirtualEnum virtual;

	public VirtualEnum getVirtual() {
		return virtual;
	}

	public void setVirtual(VirtualEnum virtual) {
		this.virtual = virtual;
	}

	public List<String> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(List<String> goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public CategoryTypeEnum getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryTypeEnum categoryType) {
		this.categoryType = categoryType;
	}

	public CategoryCodeEnum getCode() {
		return code;
	}

	public void setCode(CategoryCodeEnum code) {
		this.code = code;
	}
}
