package com.lizikj.api.vo.merchandise;

import com.lizikj.common.enums.VirtualEnum;
import com.lizikj.merchandise.enums.CategoryCodeEnum;
import com.lizikj.merchandise.enums.CategoryTypeEnum;
import com.lizikj.merchandise.enums.RemoveStatusEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author Michael.Huang
 * @date 2017/6/17
 */
@ApiModel
public class CategoryVO extends BaseDTO {
    
	private static final long serialVersionUID = 1L;

	/**
     * 分类id
     */
	@ApiModelProperty(value = "分类ID", required = true)
    private String id;

    /**
     * 分类名称
     */
	@ApiModelProperty(value = "分类名称", required = true)
    private String name;

    /**
     * 分类编码，部分固定分类需要统一编码，没有编码的说明是自定义分类
     * 此处不使用id来区分，因为在迁移数据时可能会变
     */
	@ApiModelProperty(value = "分类编码：见CategoryCodeEnum： QUICK_MENU.快捷菜单，MEMBER_PRICE.会员价美食。部分固定分类需要统一编码，没有编码的说明是自定义分类。", required = true)
    private CategoryCodeEnum code;

    /**
     * 店铺id
     */
	@ApiModelProperty(value = "店铺ID", required = true)
    private Long shopId;

    /**
     * 排序
     */
	@ApiModelProperty(value = "排序", required = true)
    private Integer order;

    /**
     * 该分类下有的商品ID
     */
	@ApiModelProperty(value = "该分类下有的商品ID", required = true)
    List<String> goodsIds;
	
    /**
     * 类型：1.连锁的分类，2.门店自有的分类
     */
	@ApiModelProperty(value = "类型：1.连锁的分类，2.门店自有的分类", required = true)
    private CategoryTypeEnum categoryType;

    /**
     * 删除状态
     */
    @ApiModelProperty(value = "删除状态，REMOVED,已完全删除,\n" +
            "    RECYCLE,回收站中,\n" +
            "    ACTIVE,激活可用状态,\n" +
            "    INVISIBLE,不可见", required = true)
    private RemoveStatusEnum removeStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;


    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;

    /**
     * 商户ID：连锁建分类时赋值
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    private VirtualEnum virtual;

    public VirtualEnum getVirtual() {
        return virtual;
    }

    public void setVirtual(VirtualEnum virtual) {
        this.virtual = virtual;
    }


    public RemoveStatusEnum getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(RemoveStatusEnum removeStatus) {
        this.removeStatus = removeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

    public CategoryCodeEnum getCode() {
        return code;
    }

    public void setCode(CategoryCodeEnum code) {
        this.code = code;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

	public List<String> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(List<String> goodsIds) {
		this.goodsIds = goodsIds;
	}

    public CategoryTypeEnum getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryTypeEnum categoryType) {
        this.categoryType = categoryType;
    }
}
