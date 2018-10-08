package com.lizikj.api.vo.member.param;

import java.util.Date;

/**
 * Created by liangxiaolin on 2017/8/10.
 */
public class DateParam {
    private Date startDate;
    private Date endDate;
    private String startDateStr;
    private String endDateStr;

    @Override
    public String toString() {
        return "DateParam{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

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

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }
}
