package com.lizikj.api.vo.merchandise.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2017 12 06 9:40
 */
@ApiModel(value = "更新美食图片")
public class UpdateGoodsImagesVO {

    @ApiModelProperty(value = "美食Id")
    private String goodsId;

    @ApiModelProperty(value = "美食图片Id")
    private List<Long> mediaIds;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public List<Long> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<Long> mediaIds) {
        this.mediaIds = mediaIds;
    }
}
