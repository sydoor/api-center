package com.lizikj.api.vo.shop.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhoufe
 * @created at 2017 08 05 16:15
 */
@ApiModel
public class RemoveToAnotherAreaParamVO implements Serializable {

    private static final long serialVersionUID = 1360303072592751479L;

    @ApiModelProperty(value = "桌台IDS", required = true)
    private List<Long> areaDeskIds;

    @ApiModelProperty(value = "目标区域ID", required = true)
    private long targetAreaId;

    public List<Long> getAreaDeskIds() {
        return areaDeskIds;
    }

    public void setAreaDeskIds(List<Long> areaDeskIds) {
        this.areaDeskIds = areaDeskIds;
    }

    public long getTargetAreaId() {
        return targetAreaId;
    }

    public void setTargetAreaId(long targetAreaId) {
        this.targetAreaId = targetAreaId;
    }
}
