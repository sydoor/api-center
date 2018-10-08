package com.lizikj.api.vo.reporting.param;

import java.util.Date;

/**
 * @author Michael.Huang
 * @date 2018/7/20 14:46
 */
public class ShopQueryBaseParamVO {


    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;


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
}
