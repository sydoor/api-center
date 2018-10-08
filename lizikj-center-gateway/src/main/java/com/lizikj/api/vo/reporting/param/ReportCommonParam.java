package com.lizikj.api.vo.reporting.param;

import com.lizikj.reporting.enums.CurrencyTypeReportEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/8/9.
 */
@ApiModel
public class ReportCommonParam {
    /**
     * 统计日期
     */
    @ApiModelProperty(value = "统计日期 yyy-MM-dd")
    private String reportDate;
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    /**
     * 统计对象（1 所有、2 商户、3 店铺）
     */
    @ApiModelProperty(value = "统计对象（1 所有、2 商户、3 店铺）",required = true)
    private Integer target;
    /**
     * 统计类型
     */
    @ApiModelProperty(value = "统计类型 8000 昨日 8100 日 8200 月 8300 年 8400 当天 8500 近七天（不包括当天） 8600 近30天（不包括当天）9999 全部")
    private Integer reportType;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间(优先取reportType)")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间（优先取reportType）")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    @ApiModelProperty(value = "货币类型")
    private CurrencyTypeReportEnum currencyTypeReportEnum;
    @ApiModelProperty(value = "开始时间 精确到毫秒")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startTime;
    @ApiModelProperty(value = "结束时间 精确到毫秒")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date endTime;

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Integer getReportType() {
        return reportType;
    }
    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }
    public Integer getTarget() {
        return target;
    }
    public void setTarget(Integer target) {
        this.target = target;
    }
    public String getReportDate() {
        return reportDate;
    }
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    public Long getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public CurrencyTypeReportEnum getCurrencyTypeReportEnum() {
        return currencyTypeReportEnum;
    }

    public void setCurrencyTypeReportEnum(CurrencyTypeReportEnum currencyTypeReportEnum) {
        this.currencyTypeReportEnum = currencyTypeReportEnum;
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

    @Override
    public String toString() {
        return "ReportCommonParam{" +
                "reportDate='" + reportDate + '\'' +
                ", merchantId=" + merchantId +
                ", shopId=" + shopId +
                ", target=" + target +
                ", reportType=" + reportType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currencyTypeReportEnum=" + currencyTypeReportEnum +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
