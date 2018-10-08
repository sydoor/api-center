package com.lizikj.api.vo.merchandise.param;

import com.lizikj.merchandise.enums.RecycleShelveTypeEnum;
import com.lizikj.version.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author zhoufe
 * @date 2017/7/24 16:39
 */
@ApiModel
public class RecycleGoodsUpdateParamVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID：连锁和门店的美食ID
     */
    private List<String> goodsIds;

    /**
     * 回收上架类型：1.回收，且上架，2.回收，但先不上架
     */
    private RecycleShelveTypeEnum recycleShelveType;

    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID", required = true)
    private String categoryId;

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }

    public RecycleShelveTypeEnum getRecycleShelveType() {
        return recycleShelveType;
    }

    public void setRecycleShelveType(RecycleShelveTypeEnum recycleShelveType) {
        this.recycleShelveType = recycleShelveType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
