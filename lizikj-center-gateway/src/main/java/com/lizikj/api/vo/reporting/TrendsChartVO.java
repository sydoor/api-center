package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *  趋势图
 * Created by liangxiaolin on 2017/9/9.
 */
@ApiModel
public class TrendsChartVO {
    /**
     * 趋势图归属
     */
    @ApiModelProperty(name = "owner",value = "趋势图归属")
    private Object owner;
    /**
     * 纵坐标值列表
     */
    @ApiModelProperty(name = "verticalAxis",value = "纵坐标值列表")
    List<String> verticalAxis;
    /**
     * 横坐标值列表
     */
    @ApiModelProperty(name = "verticalAxis",value = "横坐标值列表")
    List<String> horizontalAxis;

    public List<String> getVerticalAxis() {
        return verticalAxis;
    }

    public void setVerticalAxis(List<String> verticalAxis) {
        this.verticalAxis = verticalAxis;
    }

    public List<String> getHorizontalAxis() {
        return horizontalAxis;
    }

    public void setHorizontalAxis(List<String> horizontalAxis) {
        this.horizontalAxis = horizontalAxis;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "TrendsChartVO{" +
                "owner=" + owner +
                ", verticalAxis=" + verticalAxis +
                ", horizontalAxis=" + horizontalAxis +
                '}';
    }
}
