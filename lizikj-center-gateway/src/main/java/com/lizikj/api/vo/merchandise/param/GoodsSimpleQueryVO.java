package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2017 12 05 9:47
 */
@ApiModel(value = "PC端，根据条件查询美食")
public class GoodsSimpleQueryVO {

    @ApiModelProperty(value = "店铺id，前端不用传，从token中取")
    private Long shopId;

    @ApiModelProperty(value = "美食名称")
    private String goodsName;

    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "是否需要计算分时价格")
    private YesOrNoEnum needComputeSpecialDiscount;

    public YesOrNoEnum getNeedComputeSpecialDiscount() {
        return needComputeSpecialDiscount;
    }

    public void setNeedComputeSpecialDiscount(YesOrNoEnum needComputeSpecialDiscount) {
        this.needComputeSpecialDiscount = needComputeSpecialDiscount;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
