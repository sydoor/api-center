package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ASUS
 * @created at 2017 08 02 20:10
 */
public class SkuPackagePropertyVO {
    /**
     * 组别名称
     */
    @ApiModelProperty(value = "组别名称")
    private String aliasName;

    /**
     * 前缀数值(组别套餐有意义)
     */
    @ApiModelProperty(value = "前缀数值(组别套餐有意义)")
    private Integer frontSize;

    /**
     * 后缀数值(组别套餐有意义)
     */
    @ApiModelProperty(value = "后缀数值(组别套餐有意义)")
    private Integer backSize;

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Integer getFrontSize() {
        return frontSize;
    }

    public void setFrontSize(Integer frontSize) {
        this.frontSize = frontSize;
    }

    public Integer getBackSize() {
        return backSize;
    }

    public void setBackSize(Integer backSize) {
        this.backSize = backSize;
    }
}
