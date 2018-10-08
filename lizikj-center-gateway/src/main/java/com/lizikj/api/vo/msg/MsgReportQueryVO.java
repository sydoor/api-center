package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.MessageTypeEnum;
import com.lizikj.message.api.enums.StatusEnum;
import com.lizikj.message.api.enums.TerminalTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel
public class MsgReportQueryVO {
    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Long merchantId;
    /**
     * 店铺ID
     */
    @ApiModelProperty(value = "店铺ID")
    private Long shopId;
    /**
     * 设备号
     */
    @ApiModelProperty(value = "设备号")
    private String sn;
    /**
     * 第三方注册码
     */
    @ApiModelProperty(value = "第三方注册码")
    private String thirdRegisterId;
    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型")
    private MessageTypeEnum messageTypeEnum;

    /**
     * 终端类型
     */
    @ApiModelProperty(value = "终端类型")
    private TerminalTypeEnum terminalTypeEnum;
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
     * 是否发送成功
     */
    @ApiModelProperty(value = "是否发送成功")
    private StatusEnum sendStatus;
    @ApiModelProperty(value = "是否必达")
    private StatusEnum mustArrival;
    @ApiModelProperty(value = "到达状态")
    private StatusEnum arrivalStatus;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "商户ID")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getThirdRegisterId() {
        return thirdRegisterId;
    }

    public void setThirdRegisterId(String thirdRegisterId) {
        this.thirdRegisterId = thirdRegisterId;
    }

    public MessageTypeEnum getMessageTypeEnum() {
        return messageTypeEnum;
    }

    public void setMessageTypeEnum(MessageTypeEnum messageTypeEnum) {
        this.messageTypeEnum = messageTypeEnum;
    }

    public TerminalTypeEnum getTerminalTypeEnum() {
        return terminalTypeEnum;
    }

    public void setTerminalTypeEnum(TerminalTypeEnum terminalTypeEnum) {
        this.terminalTypeEnum = terminalTypeEnum;
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

    public StatusEnum getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(StatusEnum sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public StatusEnum getMustArrival() {
        return mustArrival;
    }

    public void setMustArrival(StatusEnum mustArrival) {
        this.mustArrival = mustArrival;
    }

    public StatusEnum getArrivalStatus() {
        return arrivalStatus;
    }

    public void setArrivalStatus(StatusEnum arrivalStatus) {
        this.arrivalStatus = arrivalStatus;
    }
}
