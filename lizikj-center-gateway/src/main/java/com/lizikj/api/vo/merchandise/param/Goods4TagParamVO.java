package com.lizikj.api.vo.merchandise.param;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 09 19 10:52
 */
public class Goods4TagParamVO {

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private List<String> goodsIds;

    /**
     * 标签ID
     */
    @ApiModelProperty(value = "标签ID", required = true)
    private String tagId;

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
