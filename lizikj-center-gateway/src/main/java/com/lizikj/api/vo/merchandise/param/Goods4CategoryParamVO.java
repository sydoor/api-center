package com.lizikj.api.vo.merchandise.param;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author zhoufe
 * @date 2017/7/24 17:15
 */
@ApiModel
public class Goods4CategoryParamVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private List<String> goodsIds;

    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID", required = true)
    private String categoryId;

    /**
     * 分类的类型：0.店铺分类，10000.快捷菜单，10001.会员价美食
     */
//    @ApiModelProperty(value = "分类的类型：0.店铺分类，10000.快捷菜单，10001.会员价美食", required = true)
//    private Integer categoryCode;


    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
