package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2018 01 11 16:12
 */
@ApiModel("PC 端，分类和对应的美食")
public class CategoryAndGoods4PCVO {

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
     * 该分类下有的商品
     */
    @ApiModelProperty(value = "该分类下的商品", required = true)
    private List<SimpleGoods4PCVO> goodsList;

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

    public List<SimpleGoods4PCVO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<SimpleGoods4PCVO> goodsList) {
        this.goodsList = goodsList;
    }
}