package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.TerminalTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @Date 2017/11/27 18:13
 */
@ApiModel
public class MsgPushStatisticQueryVO {
    /**
     * 设备号 模糊查询
     */
    @ApiModelProperty(value = "设备号 模糊查询")
    private String sn;

    /**
     * 设备号
     */
    @ApiModelProperty(value = "设备号列表")
    private List<String> sns;
    /**
     * 终端类型
     */
    @ApiModelProperty(value = "终端类型")
    private TerminalTypeEnum terminalTypeEnum;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public List<String> getSns() {
        return sns;
    }

    public void setSns(List<String> sns) {
        this.sns = sns;
    }

    public TerminalTypeEnum getTerminalTypeEnum() {
        return terminalTypeEnum;
    }

    public void setTerminalTypeEnum(TerminalTypeEnum terminalTypeEnum) {
        this.terminalTypeEnum = terminalTypeEnum;
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
