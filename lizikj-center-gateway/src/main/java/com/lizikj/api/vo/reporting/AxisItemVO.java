package com.lizikj.api.vo.reporting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  坐标KV
 * Created by liangxiaolin on 2017/9/14.
 */
@ApiModel
public class AxisItemVO {
    /**
     * 值名称
     */
    @ApiModelProperty(value = "值名称")
    private String key;
    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    private String value;

    public AxisItemVO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
