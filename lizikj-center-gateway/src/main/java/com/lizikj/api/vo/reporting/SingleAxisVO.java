package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liangxiaolin on 2017/9/16.
 */
@ApiModel
public class SingleAxisVO {
    @ApiModelProperty(value = "坐标归属")
    private String owner;
    @ApiModelProperty(value = "坐标值列表")
    private List<String> axisValues;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getAxisValues() {
        return axisValues;
    }

    public void setAxisValues(List<String> axisValues) {
        this.axisValues = axisValues;
    }
}
