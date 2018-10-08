package com.lizikj.api.vo.marketing.coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2018/7/25 15:25
 */
@ApiModel
public class GoodsSimpleRecommendVO implements Serializable {

    @ApiModelProperty(name = "mediaId", value = "图片ID")
    private Long mediaId;

    @ApiModelProperty(name = "goodsName", value = "美食名称")
    private String goodsName;

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
