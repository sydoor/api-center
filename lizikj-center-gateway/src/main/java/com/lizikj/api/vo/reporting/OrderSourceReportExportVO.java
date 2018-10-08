package com.lizikj.api.vo.reporting;

/**
 * @author lxl
 * @Date 2018/1/10 16:20
 */
public class OrderSourceReportExportVO {
    private String reportDateStr;
    private Double hereAmount;
    private Integer hereNum;
    private Double elemeAmount;
    private Integer elemeNum;
    private Double meituanAmount;
    private Integer meituanNum;


    public OrderSourceReportExportVO() {
        this.hereAmount = 0.00D;
        this.hereNum = 0;
        this.elemeAmount = 0.00D;
        this.elemeNum = 0;
        this.meituanAmount = 0.00D;
        this.meituanNum = 0;
    }

    public void add(OrderSourceReportExportVO other){
        this.hereAmount = this.hereAmount + other.getHereAmount();
        this.hereNum = this.hereNum + other.getHereNum();
        this.elemeAmount = this.elemeAmount + other.getElemeAmount();
        this.elemeNum = this.elemeNum + other.getElemeNum();
        this.meituanAmount = this.meituanAmount + other.getMeituanAmount();
        this.meituanNum = this.meituanNum + other.getMeituanNum();
    }

    public String getReportDateStr() {
        return reportDateStr;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
    }

    public Double getHereAmount() {
        return hereAmount;
    }

    public void setHereAmount(Double hereAmount) {
        this.hereAmount = hereAmount;
    }

    public Integer getHereNum() {
        return hereNum;
    }

    public void setHereNum(Integer hereNum) {
        this.hereNum = hereNum;
    }

    public Double getElemeAmount() {
        return elemeAmount;
    }

    public void setElemeAmount(Double elemeAmount) {
        this.elemeAmount = elemeAmount;
    }

    public Integer getElemeNum() {
        return elemeNum;
    }

    public void setElemeNum(Integer elemeNum) {
        this.elemeNum = elemeNum;
    }

    public Double getMeituanAmount() {
        return meituanAmount;
    }

    public void setMeituanAmount(Double meituanAmount) {
        this.meituanAmount = meituanAmount;
    }

    public Integer getMeituanNum() {
        return meituanNum;
    }

    public void setMeituanNum(Integer meituanNum) {
        this.meituanNum = meituanNum;
    }
}
