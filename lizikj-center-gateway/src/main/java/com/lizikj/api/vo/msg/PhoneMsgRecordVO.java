package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class PhoneMsgRecordVO {
    /**
     * 消息ID
     */
    @ApiModelProperty(value = "消息ID")
    private Long msgId;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "目标手机号")
    private String msgContent;
    /**
     * 目标手机号
     */
    @ApiModelProperty(value = "目标手机号")
    private String mobile;
    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private Date sendTime;
    /**
     *李子发送状态
     */
    @ApiModelProperty(value = "李子发送状态")
    private StatusEnum liziSendStatusEnum;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public StatusEnum getLiziSendStatusEnum() {
        return liziSendStatusEnum;
    }

    public void setLiziSendStatusEnum(StatusEnum liziSendStatusEnum) {
        this.liziSendStatusEnum = liziSendStatusEnum;
    }
}
