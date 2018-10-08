package com.lizikj.api.vo.reporting.param;

import com.lizikj.message.api.enums.OnlineStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/1/19 15:36
 */
@ApiModel
public class HeartBeatVO {
    @ApiModelProperty(value = "app唯一标识")
    private String sn;
    @ApiModelProperty(value = "提醒开通标志位")
    private Integer reminderBit;
    @ApiModelProperty(value = "在线状态")
    private OnlineStatusEnum onlineStatusEnum;
    @ApiModelProperty(value = "绑定第三方的用户别名")
    private String userAlias;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getReminderBit() {
        return reminderBit;
    }

    public void setReminderBit(Integer reminderBit) {
        this.reminderBit = reminderBit;
    }

    public OnlineStatusEnum getOnlineStatusEnum() {
        return onlineStatusEnum;
    }

    public void setOnlineStatusEnum(OnlineStatusEnum onlineStatusEnum) {
        this.onlineStatusEnum = onlineStatusEnum;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
}
