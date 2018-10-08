package com.lizikj.api.vo.merchandise.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2017 12 11 10:15
 */
public class UpdateCategoryOrderParamVO {

    @ApiModelProperty(value = "分类ID", required = true)
    private String categoryId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    private Integer order;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
