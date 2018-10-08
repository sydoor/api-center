package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *  多纵坐标 趋势图
 * Created by liangxiaolin on 2017/9/15.
 */
@ApiModel
public class ChartVO {
    @ApiModelProperty(value = "趋势图归属")
    private String owner;
    @ApiModelProperty(value = "趋势图纵坐标列表")
    private List<ChartAxisVO>  verticalAxis;
    @ApiModelProperty(value = "横坐标")
    private List<String> horizontalAxis;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<ChartAxisVO> getVerticalAxis() {
        return verticalAxis;
    }

    public void setVerticalAxis(List<ChartAxisVO> verticalAxis) {
        this.verticalAxis = verticalAxis;
    }

    public List<String> getHorizontalAxis() {
        return horizontalAxis;
    }

    public void setHorizontalAxis(List<String> horizontalAxis) {
        this.horizontalAxis = horizontalAxis;
    }
}
