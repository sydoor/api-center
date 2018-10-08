package com.lizikj.api.vo.reporting.param;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @Date 2018/1/16 19:34
 */
public class MerchandiseReportQueryParam {
    @ApiModelProperty(value = "美食分类ID列表")
    private List<String> categoryIds;
    @ApiModelProperty(value = "美食ID列表")
    private List<String> goodIds;
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date endTime;
    @ApiModelProperty(value = "统计类型 8000 昨日 8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）9999 全部")
    private Integer reportType;
    @ApiModelProperty(value = "topN")
    private Integer topN;
    @ApiModelProperty(value = "统计日期 yyy-MM-dd")
    private String reportDate;

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<String> getGoodIds() {
        return goodIds;
    }

    public void setGoodIds(List<String> goodIds) {
        this.goodIds = goodIds;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Integer getTopN() {
        return topN;
    }

    public void setTopN(Integer topN) {
        this.topN = topN;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
}
