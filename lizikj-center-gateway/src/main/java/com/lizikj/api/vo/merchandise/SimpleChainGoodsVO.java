package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 09 16 13:47
 */
@ApiModel(value = "美食模板简要信息")
public class SimpleChainGoodsVO {

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private String id;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    /**
     * 图片列表
     */
    @ApiModelProperty(value = "图片列表")
    private List<Long> mediaIds;

    /**
     * 物品名称
     */
    @ApiModelProperty(value = "物品名称")
    private String goodsName;

    @ApiModelProperty(value = "分类")
    private List<CategoryVO> categories;
    /**
     * 删除的时间
     */
    @ApiModelProperty(value = "删除的时间")
    private Long deleteTime;


    public List<CategoryVO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryVO> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public List<Long> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<Long> mediaIds) {
        this.mediaIds = mediaIds;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }
}
