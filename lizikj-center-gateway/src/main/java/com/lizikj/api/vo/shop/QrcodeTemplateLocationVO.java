package com.lizikj.api.vo.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhoufe
 * @date 2018/5/23 9:27
 */
@ApiModel
public class QrcodeTemplateLocationVO implements Serializable {

    //:二维码图片宽;
    @ApiModelProperty(value = "二维码图片宽(px)：10")
    private Integer qrcodeWidth;
    //:二维码图片高;     
    @ApiModelProperty(value = "二维码图片高(px)：10")
    private Integer qrcodeHeight;
    //:二维码图片X轴起点;
    @ApiModelProperty(value = "二维码图片X轴起点(px)：10")
    private Integer qrcodeX;
    //:二维码图片Y轴起点;     
    @ApiModelProperty(value = "二维码图片Y轴起点(px)：10")
    private Integer qrcodeY;
    //:桌台名称X轴起点;
    @ApiModelProperty(value = "桌台名称X轴起点(px)：10")
    private Integer deskTextX;
    //:桌台名称Y轴起点;     
    @ApiModelProperty(value = "桌台名称Y轴起点(px)：10")
    private Integer deskTextY;
    //:桌台名称宽;
    @ApiModelProperty(value = "桌台名称宽(px)：10")
    private Integer deskTextWidth;
    //:桌台名称高;     
    @ApiModelProperty(value = "桌台名称高(px)：10")
    private Integer deskTextHeight;
    //:桌台文字颜色
    @ApiModelProperty(value = "桌台文字颜色：#FFFFFF")
    private String deskTextColor;
    //:桌台文字大小
    @ApiModelProperty(value = "桌台文字大小(px)：10")
    private Integer deskTextSize;

    public Integer getQrcodeWidth() {
        return qrcodeWidth;
    }

    public void setQrcodeWidth(Integer qrcodeWidth) {
        this.qrcodeWidth = qrcodeWidth;
    }

    public Integer getQrcodeHeight() {
        return qrcodeHeight;
    }

    public void setQrcodeHeight(Integer qrcodeHeight) {
        this.qrcodeHeight = qrcodeHeight;
    }

    public Integer getQrcodeX() {
        return qrcodeX;
    }

    public void setQrcodeX(Integer qrcodeX) {
        this.qrcodeX = qrcodeX;
    }

    public Integer getQrcodeY() {
        return qrcodeY;
    }

    public void setQrcodeY(Integer qrcodeY) {
        this.qrcodeY = qrcodeY;
    }

    public Integer getDeskTextX() {
        return deskTextX;
    }

    public void setDeskTextX(Integer deskTextX) {
        this.deskTextX = deskTextX;
    }

    public Integer getDeskTextY() {
        return deskTextY;
    }

    public void setDeskTextY(Integer deskTextY) {
        this.deskTextY = deskTextY;
    }

    public Integer getDeskTextWidth() {
        return deskTextWidth;
    }

    public void setDeskTextWidth(Integer deskTextWidth) {
        this.deskTextWidth = deskTextWidth;
    }

    public Integer getDeskTextHeight() {
        return deskTextHeight;
    }

    public void setDeskTextHeight(Integer deskTextHeight) {
        this.deskTextHeight = deskTextHeight;
    }

    public String getDeskTextColor() {
        return deskTextColor;
    }

    public void setDeskTextColor(String deskTextColor) {
        this.deskTextColor = deskTextColor;
    }

    public Integer getDeskTextSize() {
        return deskTextSize;
    }

    public void setDeskTextSize(Integer deskTextSize) {
        this.deskTextSize = deskTextSize;
    }
}
