package com.lizikj.api.vo.shop.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2018/5/23 18:56
 */
@ApiModel
public class PregenQrcodeParamVO implements Serializable {

    private static final long serialVersionUID = -1678651580770332827L;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称", required = true)
    private String areaName;

    /**
     * 桌台名称的前缀
     */
    @ApiModelProperty(value = "桌台名称的前缀", required = true)
    private String deskNamePrefix;

    /**
     * 桌台名称的后缀开始
     */
    @ApiModelProperty(value = "桌台名称的后缀开始：前端一个输入框时填这里", required = true)
    private Integer suffixStart;

    /**
     * 桌台名称的后缀结束
     */
    @ApiModelProperty(value = "桌台名称的后缀结束：前端没有该输入框时填0", required = true)
    private Integer suffixEnd;

    /**
     * 座位数
     */
    @ApiModelProperty(value = "座位数：前端没有该输入框时填0", required = true)
    private Integer seatNum;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDeskNamePrefix() {
        return deskNamePrefix;
    }

    public void setDeskNamePrefix(String deskNamePrefix) {
        this.deskNamePrefix = deskNamePrefix;
    }

    public Integer getSuffixStart() {
        return suffixStart;
    }

    public void setSuffixStart(Integer suffixStart) {
        this.suffixStart = suffixStart;
    }

    public Integer getSuffixEnd() {
        return suffixEnd;
    }

    public void setSuffixEnd(Integer suffixEnd) {
        this.suffixEnd = suffixEnd;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }
}
