package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *  趋势图x/y坐标列表
 * Created by liangxiaolin on 2017/9/14.
 */
@ApiModel
public class ChartAxisVO {
    /**
     * 坐标归属
     */
    @ApiModelProperty(value = "坐标归属")
    private String axisOwner;
    /**
     * 坐标值
     */
    @ApiModelProperty(value = "坐标值")
    private String axisValue;
    /**
     * x/y坐标列表
     */
    @ApiModelProperty(value = "x/y坐标列表")
    private List<AxisItemVO> axisItems;

    public String getAxisOwner() {
        return axisOwner;
    }

    public void setAxisOwner(String axisOwner) {
        this.axisOwner = axisOwner;
    }

    public List<AxisItemVO> getAxisItems() {
        return axisItems;
    }

    public void setAxisItems(List<AxisItemVO> axisItems) {
        this.axisItems = axisItems;
    }

    public String getAxisValue() {
        return axisValue;
    }

    public void setAxisValue(String axisValue) {
        this.axisValue = axisValue;
    }
}
