package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.TerminalTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author lxl
 * @Date 2017/11/29 9:47
 */
@ApiModel
public class UserRecordQueryVO {
    /**
     * 用户ID列表
     */
    @ApiModelProperty(value = "用户ID列表")
    private List<Long> userIds;
    @ApiModelProperty(value = "设备号列表")
    private List<String> sns;
    @ApiModelProperty(value = "设备号")
    private String sn;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "终端类型")
    private TerminalTypeEnum terminalTypeEnum;


    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
