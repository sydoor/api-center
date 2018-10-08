package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 饼状图
 * Created by liangxiaolin on 2017/9/18.
 */
@ApiModel
public class PieItemVO {
    @ApiModelProperty(value = "值归属")
    private Object name;
    @ApiModelProperty(value = "值")
    private Object value;

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
