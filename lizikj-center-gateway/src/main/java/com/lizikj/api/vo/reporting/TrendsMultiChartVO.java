package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liangxiaolin on 2017/9/14.
 */
@ApiModel
public class TrendsMultiChartVO {

    @ApiModelProperty(value = "多纵坐标列表")
    private List<SingleAxisVO> verticalAxis;
    @ApiModelProperty(value = "横坐标列表")
    private List<String> horizontalAxisValues;

    public List<SingleAxisVO> getVerticalAxis() {
        return verticalAxis;
    }

    public void setVerticalAxis(List<SingleAxisVO> verticalAxis) {
        this.verticalAxis = verticalAxis;
    }

    public List<String> getHorizontalAxisValues() {
        return horizontalAxisValues;
    }

    public void setHorizontalAxisValues(List<String> horizontalAxisValues) {
        this.horizontalAxisValues = horizontalAxisValues;
    }
}
