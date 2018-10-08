package com.lizikj.api.vo.merchandise;

/**
 * @author dyh
 * @created at 2017 09 08 10:34
 */
public class ActivityInfoVO {
    private long activityId;

    /**
     * ActivityTemplateEnum
     */
    private byte templateId;

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public byte getTemplateId() {
        return templateId;
    }

    public void setTemplateId(byte templateId) {
        this.templateId = templateId;
    }
}
