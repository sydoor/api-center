package com.lizikj.api.vo.merchandise.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 09 19 10:50
 */
@ApiModel(value = "给美食加标签")
public class Goods2TagParamVO {

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private List<String> goodsIds;

    /**
     * 标签ID
     */
    @ApiModelProperty(value = "标签ID", required = true)
    private List<String> tagIds;

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }
}
