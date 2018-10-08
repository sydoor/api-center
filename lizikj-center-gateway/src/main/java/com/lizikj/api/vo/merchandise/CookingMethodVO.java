package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Michael.Huang
 * @date 2017/8/24 20:47
 */
@ApiModel(value = "做法")
public class CookingMethodVO {

    /**
     * 做法名称
     */
    @ApiModelProperty(value = "做法名称")
    private String name;

    /**
     * 是否选中激活
     */
    @ApiModelProperty(value = "是否选中激活")
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
