package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.TerminalTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2017/11/27 18:19
 */
@ApiModel
public class MsgPushStatisticVO {
    /**
     * 消息用户ID
     */
    @ApiModelProperty(value = "消息用户ID")
    private Long msgUserId;
    /**
     * 第三方注册ID
     */
    @ApiModelProperty(value = "消息用户ID")
    private String registerId;
    /**
     * 设备号
     */
    @ApiModelProperty(value = "消息用户ID")
    private String sn;
    /**
     * 终端类型
     */
    @ApiModelProperty(value = "消息用户ID")
    private TerminalTypeEnum terminalTypeEnum;
    /**
     * 发送次数
     */
    @ApiModelProperty(value = "消息用户ID")
    private Integer sendNum;
    /**
     * 成功发送次数
     */
    @ApiModelProperty(value = "消息用户ID")
    private Integer sendSucceedNum;
    /**
     * 发送失败次数
     */
    @ApiModelProperty(value = "消息用户ID")
    private Integer sendFailedNum;
    /**
     * 必达消息总数
     */
    @ApiModelProperty(value = "消息用户ID")
    private Integer mustArrivalNum;
    /**
     * 必达消息达到次数（发送成功，回调成功）
     */
    @ApiModelProperty(value = "必达消息达到次数（发送成功，回调成功）")
    private Integer mustArrivalSucceedNum;
    /**
     * 必达消息发送成功未回调次数
     */
    @ApiModelProperty(value = "必达消息发送成功未回调次数")
    private Integer mustArrivalNotConfirmNum;
    /**
     * 必达消息到达率
     */
    @ApiModelProperty(value = "必达消息到达率")
    private Double mustArrivalSucceedRate;
    /**
     * 发送成功率
     */
    @ApiModelProperty(value = "发送成功率")
    private Double sendSucceedRate;
    /**
     * 平均重试次数
     */
    @ApiModelProperty(value = "平均重试次数")
    private Double averageRetryNum;
    /**
     * 平均时延
     */
    @ApiModelProperty(value = "平均时延")
    private Long  averageDelay;

    /**
     * 重试次数
     */
    @ApiModelProperty(value = "重试次数")
    private Integer retryNum;
    /**
     * 总时延
     */
    @ApiModelProperty(value = "总时延")
    private Long delay;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public TerminalTypeEnum getTerminalTypeEnum() {
        return terminalTypeEnum;
    }

    public void setTerminalTypeEnum(TerminalTypeEnum terminalTypeEnum) {
        this.terminalTypeEnum = terminalTypeEnum;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getSendSucceedNum() {
        return sendSucceedNum;
    }

    public void setSendSucceedNum(Integer sendSucceedNum) {
        this.sendSucceedNum = sendSucceedNum;
    }

    public Integer getSendFailedNum() {
        return sendFailedNum;
    }

    public void setSendFailedNum(Integer sendFailedNum) {
        this.sendFailedNum = sendFailedNum;
    }

    public Integer getMustArrivalNum() {
        return mustArrivalNum;
    }

    public void setMustArrivalNum(Integer mustArrivalNum) {
        this.mustArrivalNum = mustArrivalNum;
    }

    public Integer getMustArrivalSucceedNum() {
        return mustArrivalSucceedNum;
    }

    public void setMustArrivalSucceedNum(Integer mustArrivalSucceedNum) {
        this.mustArrivalSucceedNum = mustArrivalSucceedNum;
    }

    public Integer getMustArrivalNotConfirmNum() {
        return mustArrivalNotConfirmNum;
    }

    public void setMustArrivalNotConfirmNum(Integer mustArrivalNotConfirmNum) {
        this.mustArrivalNotConfirmNum = mustArrivalNotConfirmNum;
    }

    public Double getMustArrivalSucceedRate() {
        return mustArrivalSucceedRate;
    }

    public void setMustArrivalSucceedRate(Double mustArrivalSucceedRate) {
        this.mustArrivalSucceedRate = mustArrivalSucceedRate;
    }

    public Double getSendSucceedRate() {
        return sendSucceedRate;
    }

    public void setSendSucceedRate(Double sendSucceedRate) {
        this.sendSucceedRate = sendSucceedRate;
    }

    public Double getAverageRetryNum() {
        return averageRetryNum;
    }

    public void setAverageRetryNum(Double averageRetryNum) {
        this.averageRetryNum = averageRetryNum;
    }

    public Long getAverageDelay() {
        return averageDelay;
    }

    public void setAverageDelay(Long averageDelay) {
        this.averageDelay = averageDelay;
    }

    public Integer getRetryNum() {
        return retryNum;
    }

    public void setRetryNum(Integer retryNum) {
        this.retryNum = retryNum;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }
}
