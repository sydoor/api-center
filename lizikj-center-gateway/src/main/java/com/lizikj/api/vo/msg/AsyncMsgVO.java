package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

@ApiModel
public class AsyncMsgVO {
    @ApiModelProperty(value = "消息推送目标群体")
    private MsgTargetGroupEnum targetGroupEnum; //消息推送目标群体
    /**
     * 终端类型
     */
    @ApiModelProperty(value = "终端类型")
    private TerminalTypeEnum terminalTypeEnum;
    @ApiModelProperty("推送目标ID列表")
    private List<String> targetIds; // 推送目标ID列表
    @ApiModelProperty("推送内容")
    private String msgContent;
    @ApiModelProperty(value = "推送标题")
    private String msgTitle;
    /**
     * 消息类型（0 message 1 ）
     */
    @ApiModelProperty(value = "推送消息类型")
    private MessageTypeEnum messageTypeEnum;
    @ApiModelProperty(value = "推送额外信息")
    private Map<String,String> extras;//推送额外消息
    @ApiModelProperty(value = "发送类型")
    private SendTypeEnum sendTypeEnum;
    @ApiModelProperty(value = "是否必达")
    private StatusEnum mustArrive;
    @ApiModelProperty(value = "是否定时")
    private StatusEnum isScheduled;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "定时发送时间")
    private Date scheduledSendTime;
    @ApiModelProperty(value = "备注")
    private String memo;

    public MsgTargetGroupEnum getTargetGroupEnum() {
        return targetGroupEnum;
    }

    public void setTargetGroupEnum(MsgTargetGroupEnum targetGroupEnum) {
        this.targetGroupEnum = targetGroupEnum;
    }

    public TerminalTypeEnum getTerminalTypeEnum() {
        return terminalTypeEnum;
    }

    public void setTerminalTypeEnum(TerminalTypeEnum terminalTypeEnum) {
        this.terminalTypeEnum = terminalTypeEnum;
    }

    public List<String> getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(List<String> targetIds) {
        this.targetIds = targetIds;
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

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public SendTypeEnum getSendTypeEnum() {
        return sendTypeEnum;
    }

    public void setSendTypeEnum(SendTypeEnum sendTypeEnum) {
        this.sendTypeEnum = sendTypeEnum;
    }

    public StatusEnum getMustArrive() {
        return mustArrive;
    }

    public void setMustArrive(StatusEnum mustArrive) {
        this.mustArrive = mustArrive;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
