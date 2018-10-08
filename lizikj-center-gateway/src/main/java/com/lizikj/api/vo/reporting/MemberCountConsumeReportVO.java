package com.lizikj.api.vo.reporting;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoufe
 * @date 2018/8/2 12:05
 */
public class MemberCountConsumeReportVO implements Serializable {
    private Long countConsumeHisId;
    private Long totalMember;
    private Long totalConsumeTimes;
    private Long totalConsumeAmount;
    private Date statisticsStartDate;
    private Date statisticsEndDate;
    private Integer memberPlatform;
    private Byte removeStatus;
    private Date createTime;
    private Date updateTime;

    public Long getCountConsumeHisId() {
        return countConsumeHisId;
    }

    public void setCountConsumeHisId(Long countConsumeHisId) {
        this.countConsumeHisId = countConsumeHisId;
    }

    public Long getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Long totalMember) {
        this.totalMember = totalMember;
    }

    public Long getTotalConsumeTimes() {
        return totalConsumeTimes;
    }

    public void setTotalConsumeTimes(Long totalConsumeTimes) {
        this.totalConsumeTimes = totalConsumeTimes;
    }

    public Long getTotalConsumeAmount() {
        return totalConsumeAmount;
    }

    public void setTotalConsumeAmount(Long totalConsumeAmount) {
        this.totalConsumeAmount = totalConsumeAmount;
    }

    public Date getStatisticsStartDate() {
        return statisticsStartDate;
    }

    public void setStatisticsStartDate(Date statisticsStartDate) {
        this.statisticsStartDate = statisticsStartDate;
    }

    public Date getStatisticsEndDate() {
        return statisticsEndDate;
    }

    public void setStatisticsEndDate(Date statisticsEndDate) {
        this.statisticsEndDate = statisticsEndDate;
    }

    public Integer getMemberPlatform() {
        return memberPlatform;
    }

    public void setMemberPlatform(Integer memberPlatform) {
        this.memberPlatform = memberPlatform;
    }

    public Byte getRemoveStatus() {
        return removeStatus;
    }

    public void setRemoveStatus(Byte removeStatus) {
        this.removeStatus = removeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
