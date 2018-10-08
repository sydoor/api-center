package com.lizikj.api.vo.merchandise.param;

import com.lizikj.api.vo.merchandise.UpdateGoodsOrderVO;
import com.lizikj.merchandise.enums.CategoryCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author dyh
 * @created at 2018 01 30 11:44
 */
@ApiModel(value = "更新美食顺序VO")
public class UpdateGoodsOrdersParam {

    @ApiModelProperty(value = "美食顺序VO")
    private List<UpdateGoodsOrderVO> updateGoodsOrderVOS;

    @ApiModelProperty(value = "分类id。更新普通美食时传。更新固定双拼，主次套餐，组合多选型套餐的顺序，分类id不用传")
    private String categoryId;

    @ApiModelProperty(value = "分类code.更新普通美食不用传。更新固定双拼，主次套餐，组合多选型套餐时传")
    private CategoryCodeEnum categoryCode;

    public CategoryCodeEnum getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(CategoryCodeEnum categoryCode) {
        this.categoryCode = categoryCode;
    }

    public List<UpdateGoodsOrderVO> getUpdateGoodsOrderVOS() {
        return updateGoodsOrderVOS;
    }

    public void setUpdateGoodsOrderVOS(List<UpdateGoodsOrderVO> updateGoodsOrderVOS) {
        this.updateGoodsOrderVOS = updateGoodsOrderVOS;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
