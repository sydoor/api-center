package com.lizikj.api.vo.merchandise;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dyh
 * @created at 2017 12 06 11:09
 */
@ApiModel(value = "PC端美食VO")
public class PCGoodsVO extends GoodsVO{

    @ApiModelProperty(value = "是否包含快捷菜单分类")
    private boolean containsQuickMenu;

    public boolean isContainsQuickMenu() {
        return containsQuickMenu;
    }

    public void setContainsQuickMenu(boolean containsQuickMenu) {
        this.containsQuickMenu = containsQuickMenu;
    }
}