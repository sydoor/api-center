package com.lizikj.api.vo.merchandise;

import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *  商品和分类
 * @author zhoufe
 * @date 2017/7/25 20:55
 */
@ApiModel
public class GoodsAndCategoryVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品", required = true)
    private List<GoodsVO> goodsVOs;

    @ApiModelProperty(value = "分类", required = true)
    private List<CategoryVO> categoryVOs;

    public List<GoodsVO> getGoodsVOs() {
        return goodsVOs;
    }

    public void setGoodsVOs(List<GoodsVO> goodsVOs) {
        this.goodsVOs = goodsVOs;
    }

    public List<CategoryVO> getCategoryVOs() {
        return categoryVOs;
    }

    public void setCategoryVOs(List<CategoryVO> categoryVOs) {
        this.categoryVOs = categoryVOs;
    }
}
