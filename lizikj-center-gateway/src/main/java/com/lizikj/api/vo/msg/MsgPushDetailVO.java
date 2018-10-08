package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.MessageTypeEnum;
import com.lizikj.message.api.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class MsgPushDetailVO {
    @ApiModelProperty(value = "消息推送记录ID")
    private Long recordId;
    /**
     * 设备号
     */
    @ApiModelProperty(value = "设备号")
    private String sn;
    /**
     * 消息ID
     */
    @ApiModelProperty(value = "消息ID")
    private Long msgId;
    /**
     * 第三方消息ID
     */
    @ApiModelProperty(value = "第三方消息ID")
    private String thirdMsgId;
    /**
     * 消息用户ID
     */
    @ApiModelProperty(value = "消息用户ID")
    private Long msgUserId;
    /**
     * 第三方注册码
     */
    @ApiModelProperty(value = "第三方注册码")
    private String registerId;
    /**
     * 是否必达
     */
    @ApiModelProperty(value = "是否必达")
    private StatusEnum mustArrive;
    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    private Date sendTime;
    /**
     * 消息送达确认时间
     */
    @ApiModelProperty(value = "消息送达确认时间")
    private Date arrivalConfirmTime;
    /**
     * 第三方接收信息时间
     */
    @ApiModelProperty(value = "第三方接收信息时间")
    private Date thirdReceivedTime;
    /**
     * 第三方确认发送成功时间
     */
    @ApiModelProperty(value = "第三方确认发送成功时间")
    private Date thirdArrivalTime;
    /**
     * 发送状态
     */
    @ApiModelProperty(value = "发送状态")
    private StatusEnum sendStatus;
    /**
     * 李子回调发送时间
     */
    @ApiModelProperty(value = "李子回调发送时间")
    private StatusEnum liziSendStatus;
    /**
     * 第三方回调发送状态
     */
    @ApiModelProperty(value = "第三方回调发送状态")
    private StatusEnum thirdSendStatus;
    /**
     * 重发次数
     */
    @ApiModelProperty(value = "重发次数")
    private Integer retryNum;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String msgContent;
    /**
     * 消息标题
     */
    @ApiModelProperty(value = "消息标题")
    private String msgTitle;
    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型")
    private MessageTypeEnum messageTypeEnum;
    /**
     * 是否定时发送
     */
    @ApiModelProperty(value = "是否定时发送")
    private StatusEnum isScheduled;
    /**
     * 定时发送时间
     */
    @ApiModelProperty(value = "定时发送时间")
    private Date scheduledSendTime;
    /**
     * 停止重复时间
     */
    @ApiModelProperty(value = "停止重复时间")
    private Date expireTime;
    /**
     * 额外消息内容
     */
    @ApiModelProperty(value = "额外消息内容")
    private String extraContent;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getThirdMsgId() {
        return thirdMsgId;
    }

    public void setThirdMsgId(String thirdMsgId) {
        this.thirdMsgId = thirdMsgId;
    }

    public Long getMsgUserId() {
        return msgUserId;
    }

    public void setMsgUserId(Long msgUserId) {
        this.msgUserId = msgUserId;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public StatusEnum getMustArrive() {
        return mustArrive;
    }

    public void setMustArrive(StatusEnum mustArrive) {
        this.mustArrive = mustArrive;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getArrivalConfirmTime() {
        return arrivalConfirmTime;
    }

    public void setArrivalConfirmTime(Date arrivalConfirmTime) {
        this.arrivalConfirmTime = arrivalConfirmTime;
    }

    public Date getThirdReceivedTime() {
        return thirdReceivedTime;
    }

    public void setThirdReceivedTime(Date thirdReceivedTime) {
        this.thirdReceivedTime = thirdReceivedTime;
    }

    public Date getThirdArrivalTime() {
        return thirdArrivalTime;
    }

    public void setThirdArrivalTime(Date thirdArrivalTime) {
        this.thirdArrivalTime = thirdArrivalTime;
    }

    public StatusEnum getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(StatusEnum sendStatus) {
        this.sendStatus = sendStatus;
    }

    public StatusEnum getLiziSendStatus() {
        return liziSendStatus;
    }

    public void setLiziSendStatus(StatusEnum liziSendStatus) {
        this.liziSendStatus = liziSendStatus;
    }

    public StatusEnum getThirdSendStatus() {
        return thirdSendStatus;
    }

    public void setThirdSendStatus(StatusEnum thirdSendStatus) {
        this.thirdSendStatus = thirdSendStatus;
    }

    public Integer getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(Integer retryNum) {
        this.retryNum = retryNum;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public MessageTypeEnum getMessageTypeEnum() {
        return messageTypeEnum;
    }

    public void setMessageTypeEnum(MessageTypeEnum messageTypeEnum) {
        this.messageTypeEnum = messageTypeEnum;
    }

    public StatusEnum getIsScheduled() {
        return isScheduled;
    }

    public void setIsScheduled(StatusEnum isScheduled) {
        this.isScheduled = isScheduled;
    }

    public Date getScheduledSendTime() {
        return scheduledSendTime;
    }

    public void setScheduledSendTime(Date scheduledSendTime) {
        this.scheduledSendTime = scheduledSendTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getExtraContent() {
        return extraContent;
    }

    public void setExtraContent(String extraContent) {
        this.extraContent = extraContent;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
}
