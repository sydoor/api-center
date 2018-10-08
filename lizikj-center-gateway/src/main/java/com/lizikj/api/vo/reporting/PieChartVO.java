package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liangxiaolin on 2017/9/18.
 */
@ApiModel
public class PieChartVO {
    @ApiModelProperty(value = "饼状图归属")
    private Object owner;
    @ApiModelProperty(value = "饼状图")
    private List<Object> subjects;
    @ApiModelProperty(value = "饼状图数据")
    private List<PieItemVO> data;

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public List<Object> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Object> subjects) {
        this.subjects = subjects;
    }

    public List<PieItemVO> getData() {
        return data;
    }

    public void setData(List<PieItemVO> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PieChartVO{" +
                "owner=" + owner +
                ", subjects=" + subjects +
                ", data=" + data +
                '}';
    }
}
