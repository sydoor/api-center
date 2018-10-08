package com.lizikj.api.vo.msg;

import com.lizikj.message.api.enums.TerminalTypeEnum;
import com.lizikj.message.api.enums.ThirdPlatformTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lxl
 * @Date 2018/3/30 9:43
 */
@ApiModel
public class MsgRegisterVO {
    @ApiModelProperty(value = "设备序列号")
    private String sn;
    @ApiModelProperty(value = "用户别名")
    private String userAlias;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "第三方类型")
    private ThirdPlatformTypeEnum thirdPlatformTypeEnum;
    @ApiModelProperty(value = "注册码")
    private String registerId;
    @ApiModelProperty(value = "终端类型")
    private TerminalTypeEnum terminalTypeEnum;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ThirdPlatformTypeEnum getThirdPlatformTypeEnum() {
        return thirdPlatformTypeEnum;
    }

    public void setThirdPlatformTypeEnum(ThirdPlatformTypeEnum thirdPlatformTypeEnum) {
        this.thirdPlatformTypeEnum = thirdPlatformTypeEnum;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public TerminalTypeEnum getTerminalTypeEnum() {
        return terminalTypeEnum;
    }

    public void setTerminalTypeEnum(TerminalTypeEnum terminalTypeEnum) {
        this.terminalTypeEnum = terminalTypeEnum;
    }
}
