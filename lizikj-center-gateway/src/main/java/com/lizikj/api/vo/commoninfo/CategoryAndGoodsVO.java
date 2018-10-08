package com.lizikj.api.vo.commoninfo;

import com.lizikj.api.vo.merchandise.GoodsVO;
import com.lizikj.merchandise.enums.CategoryCodeEnum;
import com.lizikj.merchandise.enums.CategoryTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 10 16 18:11
 */
public class CategoryAndGoodsVO {
    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类ID")
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
    @ApiModelProperty(value = "分类编码：见CategoryCodeEnum： QUICK_MENU.快捷菜单，MEMBER_PRICE.会员价美食。部分固定分类需要统一编码，没有编码的说明是自定义分类。")
    private CategoryCodeEnum code;

    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer order;

    /**
     * 该分类下有的商品
     */
    @ApiModelProperty(value = "该分类下有的商品")
    List<GoodsVO> goodsVOS;

    /**
     * 类型：1.连锁的分类，2.门店自有的分类
     */
    @ApiModelProperty(value = "类型：1.连锁的分类，2.门店自有的分类")
    private CategoryTypeEnum categoryType;

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

    public List<GoodsVO> getGoodsVOS() {
        return goodsVOS;
    }

    public void setGoodsVOS(List<GoodsVO> goodsVOS) {
        this.goodsVOS = goodsVOS;
    }

    public CategoryTypeEnum getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryTypeEnum categoryType) {
        this.categoryType = categoryType;
    }
}
